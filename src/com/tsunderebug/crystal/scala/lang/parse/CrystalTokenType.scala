package com.tsunderebug.crystal.scala.lang.parse

import com.intellij.psi.tree.IElementType
import com.tsunderebug.crystal.scala.lang.CrystalLanguage

class CrystalTokenType(debug: String) extends IElementType(debug, CrystalLanguage) {

  override def toString: String = "CrystalTokenType." + super.toString

}