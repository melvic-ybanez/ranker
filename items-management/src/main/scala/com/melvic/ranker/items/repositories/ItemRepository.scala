package com.melvic.ranker.items.repositories

import cats.free.Free
import com.melvic.ranker.items.repositories.IteRepository._
import com.melvic.ranker.models.Item
import com.melvic.ranker.implicits._
import com.melvic.ranker.utils.Page

trait ItemRepository {
  def getItem(itemId: Item.Id): ItemRepoAction[Item] =
    GetItem(itemId)

  def saveItem(item: Item): ItemRepoAction[Unit] =
    SaveItem(item)

  def listItems(predicate: Item => Boolean, page: Page): ItemRepoAction[List[Item]] =
    ListItems(predicate, page)
}

object IteRepository {
  sealed trait ItemRepoActionF[A]

  final case class GetItem(itemId: Item.Id) extends ItemRepoActionF[Item]
  final case class SaveItem(item: Item) extends ItemRepoActionF[Unit]
  final case class ListItems(predicate: Item => Boolean, page: Page) extends ItemRepoActionF[List[Item]]

  type ItemRepoAction[A] = Free[ItemRepoActionF, A]
}
