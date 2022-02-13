package com.melvic.ranker.algebras

import cats.free.Free

object ItemOperation {
  sealed trait ItemOperationF[A]

  final case class GetScore(itemId: String) extends ItemOperationF[Double]
  final case class SetScore(itemId: String, score: Double) extends ItemOperationF[Unit]
  final case class AddComment(itemId: String, comment: String) extends ItemOperationF[Unit]

  type ItemOperation[A] = Free[ItemOperationF, A]

  def getScore(itemId: String): ItemOperation[Double] =
    Free.liftF(GetScore(itemId))

  def setScore(itemId: String, score: Double): ItemOperation[Unit] =
    Free.liftF(SetScore(itemId, score))

  def addComment(itemId: String, comment: String): ItemOperation[Unit] =
    Free.liftF(AddComment(itemId, comment))

  def updateScore(itemId: String, f: Double => Double): ItemOperation[Unit] =
    for {
      currentScore <- getScore(itemId)
      newScore <- setScore(itemId, f(currentScore))
    } yield ()

  def addScore(itemId: String, points: Double): ItemOperation[Unit] =
    updateScore(itemId, _ + points)

  def subtractScore(itemId: String, points: Double): ItemOperation[Unit] =
    updateScore(itemId, _ - points)
}
