package codacy.jshint

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, StandardOpenOption}

import better.files.File
import codacy.jshint.JsHintPattern._
import com.codacy.plugins.api.results.Result.Issue
import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import com.codacy.plugins.api.{Options, Source}
import com.codacy.tools.scala.seed.utils.CommandRunner
import play.api.libs.json._

import scala.util.{Success, Try}

object JSHint extends Tool {

  private[this] implicit class PatternIdentifier(ruleId: Pattern.Id) {
    def asJsHintPattern: Option[JsHintPattern] = JsHintPattern.values.find(_.toString == ruleId.value)
  }

  private[this] lazy val RegMatch = """(.*):[ line]*([0-9]*),[ col]*([0-9]*),(.*)\((.*)\)""".r

  private[this] lazy val minusPrefix = "$minus"

  override def apply(
      source: Source.Directory,
      configuration: Option[List[Pattern.Definition]],
      files: Option[Set[Source.File]],
      options: Map[Options.Key, Options.Value]
  )(implicit specification: Tool.Specification): Try[List[Result]] = {

    def isKeep(patternId: Pattern.Id): Boolean = {
      configuration.forall(_.exists(_.patternId == patternId))
    }

    configuration
      .map { patterns =>
        fileForConfig(configFromPatterns(patterns, specification.patterns)).map(Option.apply)
      }
      .getOrElse(Success(Option.empty[Path]))
      .map { maybeConfig =>
        val configPart = maybeConfig
          .map { path =>
            List("--config", path.toAbsolutePath.toString)
          }
          .getOrElse(List.empty)
        val excludePart = List("--exclude", "./**/*.jsx")
        val finalPath = files.getOrElse(Set(source)).map(_.path)
        val cmd = List("jshint") ++ configPart ++ excludePart ++ List("--verbose") ++ finalPath

        CommandRunner.exec(cmd, Option(File(source.path).toJava)) match {
          case Right(resultFromTool) =>
            resultFromTool.stdout.map(outputLineAsResult).collect {
              case Some(result: Issue) if isKeep(result.patternId) => result
            }
          case Left(failure) => throw failure
        }
      }
  }

  private[this] def outputLineAsResult(line: String): Option[Result] =
    Option(line).collect {
      case RegMatch(file, lineNumber, _, toolMessage, error) =>
        Try(Source.Line(lineNumber.toInt)).toOption.flatMap { line =>
          ruleIdAndMessage(toolMessage, error).map {
            case (ruleId, message) =>
              Issue(Source.File(file), message, ruleId, line)
          }
        }
    }.flatten

  private[this] def configFromPatterns(
      patterns: List[Pattern.Definition],
      spec: Set[Pattern.Specification]
  ): JsObject = {
    val settings = patterns.foldLeft(BaseSettings) { (settings, pattern) =>
      def settingSet[A](param: JsHintPattern, value: A = true)(implicit writes: Writes[A]) =
        settings + ((param, Json.toJson(value)))

      def settingWithParamValue(paramName: JsHintPattern) = {

        lazy val default = spec.collectFirst {
          case patternSpec if patternSpec.patternId == pattern.patternId =>
            patternSpec.parameters.collectFirst {
              case paramSpec if paramSpec.name == Parameter.Name(paramName.toString) =>
                paramSpec.default
            }
        }.flatten

        val value = pattern.parameters
          .collectFirst {
            case paramDef if paramDef.name == Parameter.Name(paramName.toString) => paramDef.value
          }
          .orElse(default)

        value.map(settingSet(paramName, _)).getOrElse(settings)
      }

      pattern.patternId.asJsHintPattern
        .collect {
          case v @ `bitwise` =>
            settingSet(v) - `-W016`
          case v @ `camelcase` =>
            settingSet(v) - `-W106`
          case v @ `curly` =>
            settingSet(v) - `-W116`
          case v @ `eqeqeq` =>
            settingSet(v) - `-W116`
          case v @ `forin` =>
            settingSet(v) - `-W089`
          case v @ `freeze` =>
            settingSet(v) - `-W121`
          case v @ `futurehostile` =>
            settingSet(v) - `-W129`
          case v @ `immed` =>
            settingSet(v) - `-W062`
          case v @ `latedef` =>
            settingWithParamValue(v) - `-W003`
          case v @ `leanswitch` =>
            settingWithParamValue(v) - `-W145`
          case v @ `newcap` =>
            settingSet(v) - `-W055`
          case v @ `noarg` =>
            settingSet(v) - `-W059`
          case v @ `nocomma` =>
            settingSet(v) - `-W127`
          case v @ `noempty` =>
            settingSet(v) - `-W035`
          case v @ `nonbsp` =>
            settingSet(v) - `-W125`
          case v @ `nonew` =>
            settingSet(v) - `-W031`
          case v @ `plusplus` =>
            settingSet(v) - `-W016`
          case v @ `quotmark` =>
            settingSet(v) - `-W110`
          case v @ `regexpu` =>
            settingSet(v) - `-W147`
          case v @ `singleGroups` =>
            settingSet(v) - `-W126`
          case v @ `undef` =>
            settingSet(v) - `-W117`
          case v @ `unused` =>
            settingSet(v) - `-W098`
          case v @ `maxlen` =>
            settingWithParamValue(v) - `-W101`
          case v @ `trail` =>
            settingSet(v) - `-W044`
          case v @ `trailingcomma` =>
            settingSet(v) - `-W140`
          case v @ `maxparams` =>
            settingWithParamValue(v) - `-W072`
          case v @ `maxdepth` =>
            settingSet(v, 3) - `-W073`
          case v @ `maxstatements` =>
            settingWithParamValue(v) - `-W071`
          case v @ `maxcomplexity` =>
            settingWithParamValue(v) - `-W074`
          case v @ `indent` =>
            settingWithParamValue(v) - `-W015`
          case v @ `asi` =>
            settingSet(v, false) - `-W033`
          case v @ `boss` =>
            settingSet(v, false) - `-W084` - `-W093`
          case v @ `debug` =>
            settingSet(v, false) - `-W087`
          case v @ `null` =>
            settingSet(v, false) - `-W041`
          case v @ `evil` =>
            settingSet(v, false) - `-W054` - `-W060` - `-W061` - `-W066`
          case v @ `expr` =>
            settingSet(v, false) - `-W030`
          case v @ `funcscope` =>
            settingSet(v, false) - `-W038` - `-W091`
          case v @ `iterator` =>
            settingSet(v, false) - `-W104` - `-W103`
          case v @ `loopfunc` =>
            settingSet(v, false) - `-W083`
          case v @ `multistr` =>
            settingSet(v, false) - `-W043`
          case v @ `notypeof` =>
            settingSet(v, false) - `-W122`
          case v @ `shadow` =>
            settingSet(v, false) - `-E044` - `-W004`
          case v @ `proto` =>
            settingSet(v, false) - `-W103`
          case v @ `sub` =>
            settingSet(v, false) - `-W069`
          case v @ `supernew` =>
            settingSet(v, false) - `-W057`
        }
        .getOrElse(settings)
    }

    JsObject(settings.map {
      case (key, value) =>
        val rawKey = key.toString
        val correctedKey = if (rawKey.startsWith(minusPrefix)) s"-${rawKey.drop(minusPrefix.length)}" else rawKey
        (correctedKey, value)
    }.toList)
  }

  private[this] def ruleIdAndMessage(message: String, error: String): Option[(Pattern.Id, Result.Message)] = {
    val msg = message.trim

    JsHintPattern.values
      .find(_.toString == s"$minusPrefix$error")
      .collect {
        case `-W116` if msg.matches("""Expected '\{'.*""") => curly
        case `-W116` if msg.matches("""Expected '(===|!==).*""") => eqeqeq
        case `-W016` if msg.matches(""".*use of '(&|\|)'.*""") => bitwise
        case `-W106` if msg.matches(""".*not in camel case.*""") => camelcase
        case `-W003` if msg.matches(""".*was used before it was defined.*""") => latedef
        case `-W059` if msg.matches(""".*(caller|callee).*""") => noarg
        case `-W031` if msg.matches("""Do not use 'new' for side effects.*""") => nonew
        case `-W016` if msg.matches("""Unexpected use of '(\+\+|--)'.*""") => plusplus
        case `-W117` if msg.matches(""".*is not defined.*""") => undef
        case `-W098` if msg.matches(""".*is defined but never used.*""") => unused
        case `-W035` => noempty
        case `-W055` => newcap
        case `-W089` => forin
        case `-W121` => freeze
        case `-W125` => nonbsp
        case `-W126` => singleGroups
        case `-W127` => nocomma
        case `-W129` => futurehostile
        case `-W140` => trailingcomma
        case `-W062` => immed
        case `-W101` => maxlen
        case `-W110` => quotmark
        case `-W044` => trail
        case `-W072` => maxparams
        case `-W073` => maxdepth
        case `-W071` => maxstatements
        case `-W015` => indent
        case `-W074` => maxcomplexity
        case `-W033` => asi
        case `-W084` => boss
        case `-W093` => boss
        case `-W087` => debug
        case `-W041` => `null`
        case `-W054` => evil
        case `-W060` => evil
        case `-W061` => evil
        case `-W066` => evil
        case `-W030` => expr
        case `-W038` => funcscope
        case `-W091` => funcscope
        case `-W104` => iterator
        case `-W083` => loopfunc
        case `-W043` => multistr
        case `-W122` => notypeof
        case `-W004` => shadow
        case `-E044` => shadow
        case `-W103` => iterator
        case `-W069` => sub
        case `-W057` => supernew
      }
      .map { rawId =>
        val message = rawId match {
          case `immed` => "You should wrap an immediate function invocation in parenthesis."
          case _ => msg
        }

        (Pattern.Id(rawId.toString), Result.Message(message))
      }
  }

  private[this] def fileForConfig(config: JsObject) = tmpfile(".jshintrc", Json.stringify(config))

  private[this] def tmpfile(prefix: String, content: String) = {
    Try(
      Files.write(Files.createTempFile(prefix, ""), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE)
    )
  }
}
