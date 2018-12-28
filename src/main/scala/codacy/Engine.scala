package codacy

import codacy.jshint.JSHint
import com.codacy.tools.scala.seed.DockerEngine

object Engine extends DockerEngine(JSHint)()
