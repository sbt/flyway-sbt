organization := "org.flywaydb"
enablePlugins(FlywayPlugin)
name := "flyway-sbt-test5"

libraryDependencies ++= Seq(
  "org.hsqldb" % "hsqldb" % "2.7.4"
)

flywayUrl := "jdbc:hsqldb:file:target/flyway_sample;shutdown=true"
flywayUser := "SA"
flywayValidateMigrationNaming := true
