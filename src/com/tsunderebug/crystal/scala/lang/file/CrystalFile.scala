package com.tsunderebug.crystal.scala.lang.file

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.tsunderebug.crystal.scala.lang.CrystalLanguage

class CrystalFile(vp: FileViewProvider) extends PsiFileBase(vp, CrystalLanguage) {
  override def getFileType: FileType = CrystalFileType
  override def toString: String = "Crystal File"
}
