package com.melvic.ranker.models

import java.time.LocalDateTime

final case class Item(
    id: Item.Id,
    name: String,
    description: String,
    score: Double,
    createdAt: LocalDateTime,
    lastUpdate: Update,
) extends HasTimestamp

object Item {
  type Id = Int

  def fromName(name: String): Item = {
    val creationDate = LocalDateTime.now
    Item(
      id = 1,
      name,
      description = "",
      score = 0.0,
      createdAt = creationDate,
      lastUpdate = Update.fromDate(creationDate)
    )
  }
}
