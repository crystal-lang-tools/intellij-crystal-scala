package com.tsunderebug.crystal.scala.lang.parse

import com.intellij.psi.TokenType
import fastparse.all._
import fastparse.core

object Parsers {

  val all = Ztring.p

}

case class Ztring(c: String) extends CrystalTokenType("Strings")
object Ztring {

  lazy val p = (quote | paren | bracket | brace | angle).map(Ztring(_))

  lazy val quote = P( "\"" ~ (AnyChar.filter(_ != '"') | "\\\"").rep.! ~ "\"" )
  lazy val parenNest = P( "(" ~ parenNest ~ ")" ) | P( AnyChar.filter(_ != ')') | "\\)" ).rep
  lazy val paren = P( "%(" ~ parenNest.! ~ ")" )
  lazy val bracketNest = P( "[" ~ bracketNest ~ "]" ) | P( AnyChar.filter(_ != ']') | "\\]" ).rep
  lazy val bracket = P( "%[" ~ bracketNest.! ~ "]" )
  lazy val braceNest = P( "{" ~ braceNest ~ "}" ) | P( AnyChar.filter(_ != '}') | "\\}" ).rep
  lazy val brace = P( "%{" ~ braceNest.! ~ "}" )
  lazy val angleNest = P( "<" ~ angleNest ~ ">" ) | P( AnyChar.filter(_ != '>') | "\\>" ).rep
  lazy val angle = P( "%<" ~ angleNest.! ~ ">" )

}