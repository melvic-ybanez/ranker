ThisBuild / organization := "com.melvic"

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val usersManagement = (project in file("users-management"))

lazy val itemsManagement = (project in file("items-management"))
  .settings(libraryDependencies := Dependencies.catsDependencies)
  .dependsOn(shared)

lazy val shared = project

lazy val root = (project in file("."))
  .settings(
    name := "ranker",
  )
  .aggregate(usersManagement, itemsManagement)