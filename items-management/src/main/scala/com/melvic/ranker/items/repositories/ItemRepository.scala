package com.melvic.ranker.items.repositories

import cats.free.Free
import com.melvic.ranker.items.repositories.IteRepository._
import com.melvic.ranker.models.Item
import com.melvic.ranker.implicits._

trait ItemRepository {
  def getItem(itemId: Item.Id): ItemRepoOperation[Item] =
    GetItem(itemId)

  def saveItem(item: Item): ItemRepoOperation[Unit] =
    SaveItem(item)
}

object IteRepository {
  sealed trait ItemRepoOperationF[A]

  final case class GetItem(itemId: Item.Id) extends ItemRepoOperationF[Item]
  final case class SaveItem(item: Item) extends ItemRepoOperationF[Unit]

  type ItemRepoOperation[A] = Free[ItemRepoOperationF, A]
}
