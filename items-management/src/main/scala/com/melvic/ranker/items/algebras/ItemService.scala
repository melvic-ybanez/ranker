package com.melvic.ranker.items.algebras

import cats.free.Free
import com.melvic.ranker.models.Item
import ItemService._
import com.melvic.ranker.implicits._

trait ItemService {
  def getScore(itemId: Item.Id): ItemOperation[Double] =
    GetScore(itemId)

  def setScore(itemId: Item.Id, score: Double): ItemOperation[Unit] =
    SetScore(itemId, score)

  def addComment(itemId: Item.Id, comment: String): ItemOperation[Unit] =
    AddComment(itemId, comment)

  def updateScore(itemId: Item.Id, f: Double => Double): ItemOperation[Unit] =
    for {
      currentScore <- getScore(itemId)
      newScore <- setScore(itemId, f(currentScore))
    } yield ()

  def addScore(itemId: Item.Id, points: Double): ItemOperation[Unit] =
    updateScore(itemId, _ + points)

  def subtractScore(itemId: Item.Id, points: Double): ItemOperation[Unit] =
    updateScore(itemId, _ - points)
}

object ItemService {
  sealed trait ItemOperationF[A]

  final case class GetScore(itemId: Item.Id) extends ItemOperationF[Double]
  final case class SetScore(itemId: Item.Id, score: Double) extends ItemOperationF[Unit]
  final case class AddComment(itemId: Item.Id, comment: String) extends ItemOperationF[Unit]

  type ItemOperation[A] = Free[ItemOperationF, A]
}
