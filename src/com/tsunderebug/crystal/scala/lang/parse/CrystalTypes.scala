package com.tsunderebug.crystal.scala.lang.parse

import fastparse.all._
import fastparse.core

object Parsers {

  val all: core.Parser[(Int, CrystalTokenType), Char, String] = Ztring.p | Comment.c

}

case object Ztring extends CrystalTokenType("Strings") {

  lazy val p: core.Parser[(Int, Ztring.type), Char, String] = (quote | paren | bracket | brace | angle).map((s) => (s.length, Ztring))

  lazy val quote: core.Parser[String, Char, String] = P( "\"" ~ (CharIn('\u0000' to ('"' - 1).toChar, ('"' + 1).toChar to '\uFFFF') | "\\\"").rep ~ "\"" ).!
  lazy val parenNest: core.Parser[Any, Char, String] = P( "(" ~ parenNest ~ ")" ) | P( CharIn('\u0000' to (')' - 1).toChar, (')' + 1).toChar to '\uFFFF') | "\\)" ).rep
  lazy val paren: core.Parser[String, Char, String] = P( "%(" ~ parenNest ~ ")" ).!
  lazy val bracketNest: core.Parser[Any, Char, String] = P( "[" ~ bracketNest ~ "]" ) | P( CharIn('\u0000' to (']' - 1).toChar, (']' + 1).toChar to '\uFFFF') | "\\]" ).rep
  lazy val bracket: core.Parser[String, Char, String] = P( "%[" ~ bracketNest ~ "]" ).!
  lazy val braceNest: core.Parser[Any, Char, String] = P( "{" ~ braceNest ~ "}" ) | P( CharIn('\u0000' to ('}' - 1).toChar, ('}' + 1).toChar to '\uFFFF') | "\\}" ).rep
  lazy val brace: core.Parser[String, Char, String] = P( "%{" ~ braceNest ~ "}" ).!
  lazy val angleNest: core.Parser[Any, Char, String] = P( "<" ~ angleNest ~ ">" ) | P( CharIn('\u0000' to ('>' - 1).toChar, ('>' + 1).toChar to '\uFFFF') | "\\>" ).rep
  lazy val angle: core.Parser[String, Char, String] = P( "%<" ~ angleNest ~ ">" ).!

}

case object Comment extends CrystalTokenType("Comment") {

  lazy val c: core.Parser[(Int, Comment.type), Char, String] = P("#" ~ AnyChar.rep ~ End).!.map((c) => (c.length, Comment))

}