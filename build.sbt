name := "pipei"

version := "1.0-PROTOTYPE"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.28"
)

play.Project.playJavaSettings