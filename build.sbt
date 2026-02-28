lazy val repoSlug = "sbt/flyway-sbt"
lazy val flywayVersion = "12.0.3"
lazy val scala212 = "2.12.21"
lazy val scala3 = "3.8.2"

ThisBuild / organization := "com.github.sbt"
lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "flyway-sbt",
    crossScalaVersions := Seq(scala212, scala3),
    libraryDependencies ++= Seq(
      "org.flywaydb" % "flyway-core" % flywayVersion
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
    ),
    scalacOptions ++= {
      scalaBinaryVersion.value match {
        case "2.12" =>
          Seq(
            "-Xfuture"
          )
        case _ =>
          Nil
      }
    },
    pluginCrossBuild / sbtVersion := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.9.9"
        case _      => "2.0.0-RC9"
      }
    },
    Compile / doc / scalacOptions ++= {
      scalaBinaryVersion.value match {
        case "2.12" =>
          Seq(
            "-sourcepath",
            (LocalRootProject / baseDirectory).value.getAbsolutePath,
            "-doc-source-url",
            s"""https://github.com/sbt/flyway-sbt/tree/${sys.process
                .Process("git rev-parse HEAD")
                .lineStream_!
                .head}€{FILE_PATH}.scala"""
          )
        case _ => Nil
      }
    },
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++
        Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    },
    scriptedBufferLog := false,
    publishMavenStyle := true,
  )

ThisBuild / description := "An sbt plugin for Flyway database migration"
ThisBuild / developers := List(
  Developer(
    id = "davidmweber",
    name = "David Weber",
    email = "dave@veryflatcat.com",
    url = url("https://davidmweber.github.io/flyway-sbt-docs/")
  )
)
ThisBuild / licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url(s"https://github.com/$repoSlug"),
    s"scm:git@github.com:$repoSlug.git"
  )
)
ThisBuild / homepage := Some(url(s"https://github.com/$repoSlug"))
ThisBuild / githubWorkflowBuild := Seq(WorkflowStep.Sbt(List("+test", "+scripted")))
ThisBuild / githubWorkflowTargetTags ++= Seq("v*")
ThisBuild / githubWorkflowPublishTargetBranches :=
  Seq(
    RefPredicate.StartsWith(Ref.Tag("v")),
    RefPredicate.Equals(Ref.Branch("main"))
  )
ThisBuild / githubWorkflowPublish := Seq(
  WorkflowStep.Sbt(
    commands = List("ci-release"),
    name = Some("Publish project"),
    env = Map(
      "PGP_PASSPHRASE" -> "${{ secrets.PGP_PASSPHRASE }}",
      "PGP_SECRET" -> "${{ secrets.PGP_SECRET }}",
      "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
      "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}"
    )
  )
)
ThisBuild / githubWorkflowOSes := Seq("ubuntu-latest", "macos-latest")
ThisBuild / githubWorkflowJavaVersions := Seq(
  JavaSpec.temurin("17")
)
