import sbt._

object Dependencies {
  val catsVersion = "2.7.0"

  lazy val catsDependencies = Seq(
    "org.typelevel" %% "cats-core" % catsVersion,
    "org.typelevel" %% "cats-free" % catsVersion,
  )

  lazy val refinedDependencies = Seq(
    "eu.timepit" %% "refined" % "0.9.28"
  )
}
