package com.melvic.ranker.models

import java.time.LocalDateTime

final case class Update(date: LocalDateTime, comment: String)

object Update {
  def fromDate(date: LocalDateTime): Update =
    Update(date, "")
}
