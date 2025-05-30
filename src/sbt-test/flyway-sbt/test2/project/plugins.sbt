sys.props.get("plugin.version") match {
  case Some(x) => addSbtPlugin("com.github.sbt" % "flyway-sbt" % x)
  case _       => sys.error("""|The system property 'plugin.version' is not defined.
                         |Specify this property using the scriptedLaunchOpts -D.""".stripMargin)
}

libraryDependencies += "org.flywaydb" % "flyway-database-hsqldb" % "10.21.0"
