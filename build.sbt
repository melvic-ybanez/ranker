import Dependencies._

ThisBuild / organization := "com.melvic"

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val usersManagement = (project in file("users-management"))

lazy val itemsManagement = (project in file("items-management"))
  .dependsOn(shared)

lazy val topicsManagement = (project in file("topics-management"))
  .dependsOn(shared)

lazy val shared = project
  .settings(
    libraryDependencies ++= catsDependencies ++ refinedDependencies,
  )

lazy val root = (project in file("."))
  .settings(
    name := "ranker",
  )
  .aggregate(shared, usersManagement, itemsManagement, topicsManagement)