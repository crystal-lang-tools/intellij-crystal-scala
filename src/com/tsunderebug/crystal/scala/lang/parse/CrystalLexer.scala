package com.tsunderebug.crystal.scala.lang.parse

import com.intellij.lexer.{Lexer, LexerPosition}
import com.intellij.psi.tree.IElementType

class CrystalLexer() extends Lexer {

  var p: LexerPosition = _
  var c: CharSequence = _
  var s: Int = _
  var e: Int = _

  override def getTokenType: IElementType = Parsers.all.parse(c.toString, p.getOffset).get.value._2

  override def restore(lexerPosition: LexerPosition): Unit = p = lexerPosition

  override def start(charSequence: CharSequence, s: Int, e: Int, st: Int): Unit = {
    c = charSequence
    this.s = s
    this.e = e
    p = new LexerPosition {

      override def getState: Int = st

      override def getOffset: Int = s

    }
  }

  override def getCurrentPosition: LexerPosition = p

  override def advance(): Unit = p = new LexerPosition {

    override def getState: Int = p.getState

    override def getOffset: Int = p.getOffset + Parsers.all.parse(c.toString, p.getOffset).get.value._1

  }

  override def getBufferEnd: Int = e

  override def getBufferSequence: CharSequence = c

  override def getState: Int = p.getState

  override def getTokenEnd: Int = p.getOffset + Parsers.all.parse(c.toString, p.getOffset).get.value._1

  override def getTokenStart: Int = p.getOffset

}
