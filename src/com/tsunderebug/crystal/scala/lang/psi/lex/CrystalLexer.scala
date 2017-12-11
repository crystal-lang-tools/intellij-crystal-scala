package com.tsunderebug.crystal.scala.lang.psi.lex

import com.intellij.lexer.{Lexer, LexerPosition}
import com.intellij.psi.tree.IElementType
import com.tsunderebug.crystal.scala.lang.psi.{BadParse, Parsers}

import scala.collection.mutable.ListBuffer

class CrystalLexer() extends Lexer {

  var p: LexerPosition = _
  var c: CharSequence = _
  var s: Int = _
  var e: Int = _
  val bhb: ListBuffer[Int] = ListBuffer()

  override def getTokenType: IElementType = token._2

  def token: (Int, IElementType) = {
    if(p.getOffset >= e || c.length() == 0 || e - s < 1 || p.getOffset < 0) {
      (0, null)
    } else {
      try {
        bhb += p.getOffset
        Parsers.all.parse(c.toString, p.getOffset).get.value
      } catch {
        case _: fastparse.core.ParseError[_, _] =>
          if(bhb contains p.getOffset) {
            bhb.clear()
            (1, BadParse)
          } else {
            (-1, BadParse)
          }
      }
    }
  }

  override def restore(lexerPosition: LexerPosition): Unit = p = lexerPosition

  override def start(charSequence: CharSequence, s: Int, e: Int, st: Int): Unit = {
    c = charSequence
    this.s = s
    this.e = e
    bhb.clear()
    p = new LexerPosition {

      override def getState: Int = st

      override def getOffset: Int = s

    }
  }

  override def getCurrentPosition: LexerPosition = p

  override def advance(): Unit = {
    val ps = p.getState
    val po = p.getOffset
    val no = po + token._1
    println(s"advanced $ps $po $no $s $e in `$c`")
    p = new LexerPosition {

      override def getState: Int = ps

      override def getOffset: Int = no

    }
  }

  override def getBufferEnd: Int = e

  override def getBufferSequence: CharSequence = c

  override def getState: Int = p.getState

  override def getTokenEnd: Int = p.getOffset + token._1

  override def getTokenStart: Int = p.getOffset

}
