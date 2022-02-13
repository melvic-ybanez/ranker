package com.melvic.ranker.models

final case class Item(
    id: Item.Id,
    name: String,
    description: String,
    score: Double
)

object Item {
  type Id = Int

  def fromName(name: String): Item =
    Item(id = 1, name = name, description = "", score = 0.0)
}
