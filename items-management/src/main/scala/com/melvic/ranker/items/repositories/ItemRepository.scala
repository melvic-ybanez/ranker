package com.melvic.ranker.items.repositories

import cats.free.Free
import com.melvic.ranker.items.repositories.IteRepository._
import com.melvic.ranker.models.Item
import com.melvic.ranker.implicits._
import com.melvic.ranker.utils.Page

trait ItemRepository {
  def getItem(itemId: Item.Id): Action[Item] =
    GetItem(itemId)

  def saveItem(item: Item): Action[Unit] =
    SaveItem(item)

  def listItems(predicate: Item => Boolean, page: Page): Action[List[Item]] =
    ListItems(predicate, page)
}

object IteRepository {
  sealed trait ActionF[A]

  final case class GetItem(itemId: Item.Id) extends ActionF[Item]
  final case class SaveItem(item: Item) extends ActionF[Unit]
  final case class ListItems(predicate: Item => Boolean, page: Page) extends ActionF[List[Item]]

  type Action[A] = Free[ActionF, A]
}
