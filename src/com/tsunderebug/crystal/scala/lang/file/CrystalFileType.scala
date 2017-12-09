package com.tsunderebug.crystal.scala.lang.file

import javax.swing.Icon

import com.intellij.openapi.fileTypes.LanguageFileType
import com.tsunderebug.crystal.scala.CrystalIcons
import com.tsunderebug.crystal.scala.lang.CrystalLanguage

object CrystalFileType extends LanguageFileType(CrystalLanguage) {

  override def getName: String = "Crystal"
  override def getDescription: String = "Crystal language file"
  override def getIcon: Icon = CrystalIcons.crystalFile
  override def getDefaultExtension: String = "cr"

}