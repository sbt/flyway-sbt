package flywaysbt

import java.nio.file.{ Path => NioPath }
import java.io.File
import sbt.Attributed
import xsbti.FileConverter

private[flywaysbt] object PluginCompat {
  type FileRef = java.io.File
  type Out = java.io.File

  def toNioPath(a: Attributed[File])(implicit conv: FileConverter): NioPath =
    a.data.toPath()
}
