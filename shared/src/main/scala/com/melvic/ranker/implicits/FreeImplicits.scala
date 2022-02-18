package com.melvic.ranker.implicits

import cats.free.Free

import scala.language.implicitConversions

class FreeImplicits {
  implicit def liftToFree[F[_], A](a: F[A]): Free[F, A] =
    Free.liftF(a)
}
