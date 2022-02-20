package com.melvic.ranker.utils

import com.melvic.ranker.utils.Types.UInt
import eu.timepit.refined.auto._

final case class Page private (from: Int, to: Int)

object Page {
  val DefaultPageSize = 20

  def fromStartAndSize(start: UInt, size: UInt): Page =
    Page(start, start + size)

  def fromStartAndEnd(start: Int, end: Int): Either[PageError, Page] =
    if (end < start) Left(InvalidRange(start, end))
    else Right(Page(start, end))

  sealed trait PageError
  final case class InvalidRange(start: Int, end: Int) extends PageError
}
