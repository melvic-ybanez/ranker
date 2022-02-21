package com.melvic.ranker.topics.repositories

import com.melvic.ranker.models.Topic
import com.melvic.ranker.topics.repositories.TopicRepository._
import com.melvic.ranker.utils.Page
import com.melvic.ranker.implicits._

trait TopicRepository {
  def saveTopic(topic: Topic): Action[Unit] =
    SaveTopic(topic)

  def getTopic(topicId: Topic.Id): Action[Topic] =
    GetTopic(topicId)

  def listTopics(predicate: Topic => Boolean, page: Page): Action[List[Topic]] =
    ListTopics(predicate, page)
}

object TopicRepository {
  sealed trait ActionF[A]

  final case class SaveTopic(topic: Topic) extends ActionF[Unit]
  final case class GetTopic(topicId: Topic.Id) extends ActionF[Topic]
  final case class ListTopics(predicate: Topic => Boolean, page: Page) extends ActionF[List[Topic]]

  type Action[A] = ActionF[A]
}
