ThisBuild / organization := "com.melvic"

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val users = (project in file("users"))

lazy val root = (project in file("."))
  .settings(
    name := "ranker"
  )
  .aggregate(users)
