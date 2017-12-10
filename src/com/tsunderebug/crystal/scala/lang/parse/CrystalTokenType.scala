package com.tsunderebug.crystal.scala.lang.parse

import com.intellij.psi.tree.IElementType
import com.tsunderebug.crystal.scala.lang.CrystalLanguage

abstract class CrystalTokenType(debug: String) extends IElementType(debug, CrystalLanguage) {

  override def toString: String = "CrystalTokenType." + super.toString
  def length: Int

}