organization := "org.flywaydb"
enablePlugins(FlywayPlugin)
name := "flyway-sbt-test3"

libraryDependencies ++= Seq(
  "org.hsqldb" % "hsqldb" % "2.5.0"
)

flywayUrl := "jdbc:hsqldb:file:target/flyway_sample;shutdown=true"
flywayUser := "SA"
flywayLocations := Seq("filesystem:src/main/resources/db/migration")
Test / flywayUrl := "jdbc:hsqldb:file:target/flyway_sample;shutdown=true"
Test / flywayUser := "SA"
Test / flywayLocations := Seq("filesystem:src/main/resources/db/migration")
flywayCleanDisabled := false
Test / flywayCleanDisabled := false
