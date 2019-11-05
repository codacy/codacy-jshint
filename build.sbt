import com.typesafe.sbt.packager.docker.Cmd
import sjsonnew._
import sjsonnew.BasicJsonProtocol._
import sjsonnew.support.scalajson.unsafe._

name := "codacy-jshint"

scalaVersion := "2.12.10"

lazy val toolVersion = settingKey[String]("The version of the underlying tool retrieved from patterns.json")

toolVersion := {
  case class Patterns(name: String, version: String)
  implicit val patternsIso: IsoLList[Patterns] =
    LList.isoCurried((p: Patterns) => ("name", p.name) :*: ("version", p.version) :*: LNil) {
      case (_, n) :*: (_, v) :*: LNil => Patterns(n, v)
    }

  val jsonFile = (resourceDirectory in Compile).value / "docs" / "patterns.json"
  val json = Parser.parseFromFile(jsonFile)
  val patterns = json.flatMap(Converter.fromJson[Patterns])
  patterns.get.version
}

libraryDependencies += "com.codacy" %% "codacy-engine-scala-seed" % "3.1.0"

enablePlugins(AshScriptPlugin)

enablePlugins(DockerPlugin)

def installAll(toolVersion: String) =
  s"""apk update &&
     |apk add nodejs-npm &&
     |npm install -g jshint@$toolVersion &&
     |rm -rf /tmp/* &&
     |rm -rf /var/cache/apk/*""".stripMargin
    .replaceAll(System.lineSeparator(), " ")

Universal / mappings ++= {
  (Compile / resourceDirectory).map { resourceDir: File =>
    val src = resourceDir / "docs"
    val dest = "/docs"

    for {
      path <- src.allPaths.get if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
  }
}.value

val dockerUser = "docker"
val dockerGroup = "docker"

daemonUser in Docker := dockerUser

daemonGroup in Docker := dockerGroup

dockerBaseImage := "openjdk:8-jre-alpine"

dockerCommands := dockerCommands.value.flatMap {
  case cmd @ Cmd("WORKDIR", _) => Seq(Cmd("WORKDIR", "/src"))
  case cmd @ Cmd("ADD", _) =>
    Seq(
      Cmd("RUN", s"adduser -u 2004 -D $dockerUser"),
      cmd,
      Cmd("RUN", installAll(toolVersion.value)),
      Cmd("ENV", "NODE_PATH /usr/lib/node_modules"),
      Cmd("RUN", "mv /opt/docker/docs /docs")
    )
  case other => List(other)
}
