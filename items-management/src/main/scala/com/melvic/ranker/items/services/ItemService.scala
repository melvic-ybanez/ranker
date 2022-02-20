package com.melvic.ranker.items.services

import cats.free.Free
import com.melvic.ranker.models.Item
import ItemService._
import com.melvic.ranker.implicits._
import com.melvic.ranker.utils.Page

trait ItemService {
  def getScore(itemId: Item.Id): Action[Double] =
    GetScore(itemId)

  def setScore(itemId: Item.Id, score: Double): Action[Unit] =
    SetScore(itemId, score)

  def addComment(itemId: Item.Id, comment: String): Action[Unit] =
    AddComment(itemId, comment)

  def addItem(item: Item): Action[Unit] =
    AddItem(item)

  def listItems(predicate: Item => Boolean, page: Page): Action[List[Item]] =
    ListItems(predicate, page)

  def updateScore(itemId: Item.Id, f: Double => Double): Action[Unit] =
    for {
      currentScore <- getScore(itemId)
      newScore <- setScore(itemId, f(currentScore))
    } yield ()

  def addScore(itemId: Item.Id, points: Double): Action[Unit] =
    updateScore(itemId, _ + points)

  def subtractScore(itemId: Item.Id, points: Double): Action[Unit] =
    updateScore(itemId, _ - points)
}

object ItemService {
  sealed trait ActionF[A]

  final case class GetScore(itemId: Item.Id) extends ActionF[Double]
  final case class SetScore(itemId: Item.Id, score: Double) extends ActionF[Unit]
  final case class AddComment(itemId: Item.Id, comment: String) extends ActionF[Unit]
  final case class AddItem(item: Item) extends ActionF[Unit]
  final case class ListItems(predicate: Item => Boolean, page: Page) extends ActionF[List[Item]]

  type Action[A] = Free[ActionF, A]
}
