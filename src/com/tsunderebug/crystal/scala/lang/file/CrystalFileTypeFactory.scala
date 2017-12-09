package com.tsunderebug.crystal.scala.lang.file

import com.intellij.openapi.fileTypes.{FileTypeConsumer, FileTypeFactory}

class CrystalFileTypeFactory extends FileTypeFactory {

  override def createFileTypes(fileTypeConsumer: FileTypeConsumer): Unit = fileTypeConsumer.consume(CrystalFileType, "cr")

}