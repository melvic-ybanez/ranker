package com.melvic.ranker.models

import java.time.LocalDateTime

final case class Topic(
    topic: Topic.Id,
    name: String,
    description: String,
    createdAt: LocalDateTime,
    lastUpdate: Update
) extends HasTimestamp

object Topic {
  type Id = Int
}
