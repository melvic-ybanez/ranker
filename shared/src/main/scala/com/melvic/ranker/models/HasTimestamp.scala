package com.melvic.ranker.models

import java.time.LocalDateTime

trait HasTimestamp {
  def createdAt: LocalDateTime

  def lastUpdate: Update
}
