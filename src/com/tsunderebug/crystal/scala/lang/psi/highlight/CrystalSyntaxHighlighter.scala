package com.tsunderebug.crystal.scala.lang.psi.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.{DefaultLanguageHighlighterColors, HighlighterColors}
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.tsunderebug.crystal.scala.lang.psi.{BadParse, Charr, Comment, False, Nil, True, Variable, Ztring}
import com.tsunderebug.crystal.scala.lang.psi.lex.CrystalLexer

class CrystalSyntaxHighlighter extends SyntaxHighlighterBase {

  override def getTokenHighlights(t: IElementType): Array[TextAttributesKey] = {
    println(s"highlights $t")
    t match {
      case Variable => CrystalSyntaxHighlighter.variables
      case Ztring | Charr => CrystalSyntaxHighlighter.strings
      case Comment => CrystalSyntaxHighlighter.comments
      case Nil | True | False => CrystalSyntaxHighlighter.keys
      case BadParse => CrystalSyntaxHighlighter.errors
      case _ => CrystalSyntaxHighlighter.emptyKeys
    }
  }

  override def getHighlightingLexer: Lexer = new CrystalLexer

}

object CrystalSyntaxHighlighter {

  import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey

  val variable: TextAttributesKey = createTextAttributesKey("CRYSTAL_VARIABLE", DefaultLanguageHighlighterColors.IDENTIFIER)
  // INSTANCE_VAR is LOCAL_VARIABLE
  // GLOBAL_VAR is GLOBAL_VARIABLE
  val string: TextAttributesKey = createTextAttributesKey("CRYSTAL_STRING", DefaultLanguageHighlighterColors.STRING)
  val comment: TextAttributesKey = createTextAttributesKey("CRYSTAL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
  val key: TextAttributesKey = createTextAttributesKey("CRYSTAL_KEYS", DefaultLanguageHighlighterColors.KEYWORD)

  val error: TextAttributesKey = createTextAttributesKey("CRYSTAL_BAD_PARSE", HighlighterColors.BAD_CHARACTER)

  val emptyKeys: Array[TextAttributesKey] = Array()
  val variables: Array[TextAttributesKey] = Array(variable)
  val strings: Array[TextAttributesKey] = Array(string)
  val comments: Array[TextAttributesKey] = Array(comment)
  val keys: Array[TextAttributesKey] = Array(key)
  val errors: Array[TextAttributesKey] = Array(error)

}
