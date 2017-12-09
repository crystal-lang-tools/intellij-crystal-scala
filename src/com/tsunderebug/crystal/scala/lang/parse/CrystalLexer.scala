package com.tsunderebug.crystal.scala.lang.parse

import com.intellij.lexer.{Lexer, LexerPosition}
import com.intellij.psi.tree.IElementType

class CrystalLexer() extends Lexer {

  var p: LexerPosition = _
  var c: CharSequence = _

  override def getTokenType: IElementType = Parsers.all.parse(c.toString, p.getOffset).get.value

  override def restore(lexerPosition: LexerPosition): Unit = p = lexerPosition

  override def start(charSequence: CharSequence, s: Int, e: Int, st: Int): Unit = {
    c = charSequence
  }

  override def getCurrentPosition: LexerPosition = ???

  override def advance(): Unit = ???

  override def getBufferEnd: Int = ???

  override def getBufferSequence: CharSequence = ???

  override def getState: Int = ???

  override def getTokenEnd: Int = ???

  override def getTokenStart: Int = ???

}
