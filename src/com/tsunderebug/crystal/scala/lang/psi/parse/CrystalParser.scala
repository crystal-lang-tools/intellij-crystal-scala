package com.tsunderebug.crystal.scala.lang.psi.parse

import com.intellij.lang.parser.GeneratedParserUtilBase._
import com.intellij.lang.{ASTNode, LightPsiParser, PsiBuilder, PsiParser}
import com.intellij.psi.tree.IElementType
import com.tsunderebug.crystal.scala.lang.psi.{Charr, False, Nil, Numberr, True, Ztring, Symbol}

class CrystalParser extends PsiParser with LightPsiParser {

  override def parse(iElementType: IElementType, psiBuilder: PsiBuilder): ASTNode = {
    parseLight(iElementType, psiBuilder)
    psiBuilder.getTreeBuilt
  }

  protected def parse_root_(t: IElementType, b: PsiBuilder, l: Int): Boolean = CrystalParser.root(b, l + 1)

  override def parseLight(t: IElementType, b: PsiBuilder): Unit = {
    var r = false
    val nb = adapt_builder_(t, b, this, null)
    val m = enter_section_(nb, 0, _COLLAPSE_, null)
    r = parse_root_(t, nb, 0)
    exit_section_(nb, 0, m, t, r, true, TRUE_CONDITION)
  }

}

object CrystalParser {

  def literal(b: PsiBuilder, l: Int): Boolean = {
    if (!recursion_guard_(b, l, "literal")) return false
    val m = enter_section_(b)
    val r = Seq(Nil, True, False, Numberr, Charr, Ztring, Symbol).exists(consumeToken(b, _))
    exit_section_(b, m, null, r)
    r
  }

  def root(b: PsiBuilder, l: Int): Boolean = {
    if (!recursion_guard_(b, l, "root")) return false
    var c = current_position_(b)
    var tob = false
    while (!tob) {
      if (!literal(b, l + 1)) {
        tob = true
        if (!tob && !empty_element_parsed_guard_(b, "root", c)) {
          tob = true
          if(!tob) c = current_position_(b)
        }
      }
    }
    true
  }
}