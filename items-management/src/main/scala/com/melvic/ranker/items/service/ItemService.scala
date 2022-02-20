package com.melvic.ranker.items.service

import cats.free.Free
import com.melvic.ranker.models.Item
import ItemService._
import com.melvic.ranker.implicits._
import com.melvic.ranker.utils.Page

trait ItemService {
  def getScore(itemId: Item.Id): ItemAction[Double] =
    GetScore(itemId)

  def setScore(itemId: Item.Id, score: Double): ItemAction[Unit] =
    SetScore(itemId, score)

  def addComment(itemId: Item.Id, comment: String): ItemAction[Unit] =
    AddComment(itemId, comment)

  def addItem(item: Item): ItemAction[Unit] =
    AddItem(item)

  def listItems(predicate: Item => Boolean, page: Page): ItemAction[List[Item]] =
    ListItems(predicate, page)

  def updateScore(itemId: Item.Id, f: Double => Double): ItemAction[Unit] =
    for {
      currentScore <- getScore(itemId)
      newScore <- setScore(itemId, f(currentScore))
    } yield ()

  def addScore(itemId: Item.Id, points: Double): ItemAction[Unit] =
    updateScore(itemId, _ + points)

  def subtractScore(itemId: Item.Id, points: Double): ItemAction[Unit] =
    updateScore(itemId, _ - points)
}

object ItemService {
  sealed trait ItemActionF[A]

  final case class GetScore(itemId: Item.Id) extends ItemActionF[Double]
  final case class SetScore(itemId: Item.Id, score: Double) extends ItemActionF[Unit]
  final case class AddComment(itemId: Item.Id, comment: String) extends ItemActionF[Unit]
  final case class AddItem(item: Item) extends ItemActionF[Unit]
  final case class ListItems(predicate: Item => Boolean, page: Page) extends ItemActionF[List[Item]]

  type ItemAction[A] = Free[ItemActionF, A]
}
