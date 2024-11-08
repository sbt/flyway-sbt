package flywaysbt

import java.nio.file.{ Path => NioPath }
import sbt.*
import xsbti.{ FileConverter, HashedVirtualFileRef, VirtualFile }

private[flywaysbt] object PluginCompat:
  type FileRef = HashedVirtualFileRef
  type Out = VirtualFile

  def toNioPath(a: Attributed[HashedVirtualFileRef])(using conv: FileConverter): NioPath =
    conv.toPath(a.data)
end PluginCompat
