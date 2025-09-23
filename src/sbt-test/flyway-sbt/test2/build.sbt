organization := "org.flywaydb"
enablePlugins(FlywayPlugin)
name := "flyway-sbt-test2"

libraryDependencies ++= Seq(
  "org.hsqldb" % "hsqldb" % "2.7.4"
)

flywayUrl := "jdbc:hsqldb:file:target/flyway_sample;shutdown=true"
flywayUser := "SA"
flywayLocations += "db/migration"
Test / flywayUrl := "jdbc:hsqldb:file:target/flyway_sample;shutdown=true"
Test / flywayUser := "SA"
flywayCleanDisabled := false
Test / flywayCleanDisabled := false
