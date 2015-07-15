import com.typesafe.sbt.packager.docker._

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

name := """codacy-engine-jshint"""

version := "1.0-SNAPSHOT"

val languageVersion = "2.11.7"

scalaVersion := languageVersion

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.3.8"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

val JAVA_VERSION_MAJOR = 8

val JAVA_VERSION_MINOR = 45

val JAVA_VERSION_BUILD = 14

val JAVA_PACKAGE       = "server-jre"

val jreFilenameGzip = s"$JAVA_PACKAGE-$JAVA_VERSION_MAJOR" + s"u$JAVA_VERSION_MINOR-linux-x64.tar.gz"

val jreUrl = s"http://download.oracle.com/otn-pub/java/jdk/$JAVA_VERSION_MAJOR" + s"""u$JAVA_VERSION_MINOR""" + s"""-b$JAVA_VERSION_BUILD/$jreFilenameGzip"""

val jreExtractedFolderName = s"""jdk1.$JAVA_VERSION_MAJOR.0_$JAVA_VERSION_MINOR/jre"""

val installAll =
  s"""apk update && apk add bash curl nodejs python &&
    |npm install -g jshint""".stripMargin.replaceAll(System.lineSeparator()," ")

mappings in Universal <++= (resourceDirectory in Compile) map { (resourceDir: File) =>
  val src = resourceDir / "docs"
  val dest = "/docs"

  for {
      path <- (src ***).get
      if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
}

daemonUser in Docker := "root"

dockerBaseImage := "frolvlad/alpine-oraclejdk8"

dockerCommands := dockerCommands.value.flatMap{
  case cmd@Cmd("FROM",_) => List(cmd,Cmd("RUN", installAll))
  case add@Cmd("ADD","opt /opt") => List(add,Cmd("RUN","mv /opt/docker/docs /docs"))
  case other => List(other)
}