package com.melvic.ranker.topics.services

import cats.free.Free
import com.melvic.ranker.models.Topic
import com.melvic.ranker.topics.services.TopicService.{Action, AddTopic, GetTopic, ListTopics}
import com.melvic.ranker.utils.Page
import com.melvic.ranker.implicits._

trait TopicService {
  def addTopic(topic: Topic): Action[Unit] =
    AddTopic(topic)

  def getTopic(topicId: Topic.Id): Action[Topic] =
    GetTopic(topicId)

  def listTopic(predicate: Topic => Boolean, page: Page): Action[List[Topic]] =
    ListTopics(predicate, page)
}

object TopicService {
  sealed trait ActionF[A]

  final case class AddTopic(topic: Topic) extends ActionF[Unit]
  final case class GetTopic(topicId: Topic.Id) extends ActionF[Topic]
  final case class ListTopics(predicate: Topic => Boolean, page: Page) extends ActionF[List[Topic]]

  type Action[A] = Free[ActionF, A]
}
