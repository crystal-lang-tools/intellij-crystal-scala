package com.tsunderebug.crystal.scala.lang.psi

import fastparse.all._
import fastparse.core

object Parsers {

  val all: core.Parser[(Int, CrystalTokenType), Char, String] =
    Variable.variable |
      Symbol.sy |
      Charr.c |
      Ztring.p |
      Numberr.num |
      Comment.c |
      Nil.nil |
      True.tr |
      False.fa |
      Namespace.ns |
      Whitespace.w |
      Ended.eof

}

case object Ztring extends CrystalTokenType("string") {

  lazy val p: core.Parser[(Int, Ztring.type), Char, String] = (quote | paren | bracket | brace | angle).map((s) => (s.length, Ztring))

  lazy val quote: core.Parser[String, Char, String] = P("\"" ~ (CharIn('\u0000' to ('"' - 1).toChar, ('"' + 1).toChar to '\uFFFF') | "\\\"").rep ~ "\"").!
  lazy val parenNest: core.Parser[Any, Char, String] = P("(" ~ parenNest ~ ")" | CharIn('\u0000' to (')' - 1).toChar, (')' + 1).toChar to '\uFFFF') | "\\)").rep
  lazy val paren: core.Parser[String, Char, String] = P("%(" ~ parenNest ~ ")").!
  lazy val bracketNest: core.Parser[Any, Char, String] = P("[" ~ bracketNest ~ "]" | CharIn('\u0000' to (']' - 1).toChar, (']' + 1).toChar to '\uFFFF') | "\\]").rep
  lazy val bracket: core.Parser[String, Char, String] = P("%[" ~ bracketNest ~ "]").!
  lazy val braceNest: core.Parser[Any, Char, String] = P("{" ~ braceNest ~ "}" | CharIn('\u0000' to ('}' - 1).toChar, ('}' + 1).toChar to '\uFFFF') | "\\}").rep
  lazy val brace: core.Parser[String, Char, String] = P("%{" ~ braceNest ~ "}").!
  lazy val angleNest: core.Parser[Any, Char, String] = P("<" ~ angleNest ~ ">" | CharIn('\u0000' to ('>' - 1).toChar, ('>' + 1).toChar to '\uFFFF') | "\\>").rep
  lazy val angle: core.Parser[String, Char, String] = P("%<" ~ angleNest ~ ">").!

}

case object Comment extends CrystalTokenType("comment") {
  lazy val c: core.Parser[(Int, Comment.type), Char, String] = P("#" ~ CharIn('\u0000' to ('\n' - 1).toChar, ('\n' + 1).toChar to '\uFFFF').rep ~ ("\n" | End)).!.map((c) => (c.length, Comment))
}

case object Whitespace extends CrystalTokenType("whitespace") {
  lazy val w: core.Parser[(Int, Whitespace.type), Char, String] = P(CharsWhileIn(" \t\r\n").rep(1)).!.map((w) => (w.length, Whitespace))
}

case object Nil extends CrystalTokenType("nil") {
  lazy val nil: core.Parser[(Int, Nil.type), Char, String] = P("nil").!.map((n) => (n.length, Nil))
}

case object Symbol extends CrystalTokenType("symbol") {
  lazy val sy: core.Parser[(Int, Symbol.type), Char, String] = P(":" ~ (CharIn('a' to 'z', 'A' to 'Z') | "_").rep(1)).!.map((s) => (s.length, Symbol))
}

case object True extends CrystalTokenType("true") {
  lazy val tr: core.Parser[(Int, True.type), Char, String] = P("true").!.map((t) => (t.length, True))
}

case object False extends CrystalTokenType("false") {
  lazy val fa: core.Parser[(Int, False.type), Char, String] = P("false").!.map((f) => (f.length, False))
}

case object Numberr extends CrystalTokenType("number") {
  lazy val num: core.Parser[(Int, Numberr.type), Char, String] = P((CharIn('0' to '9') | "_").rep(1) ~ ("." ~ (CharIn('0' to '9') | "_")).? ~ ("_" ~ ("u" | "i") ~ ("8" | "16" | "32" | "64")).?).!.map((n) => (n.length, Numberr))
}

case object Charr extends CrystalTokenType("char") {
  lazy val c: core.Parser[(Int, Charr.type), Char, String] = P("'" ~ (CharIn('\u0000' to ('\n' - 1).toChar, ('\n' + 1).toChar to '\uFFFF') | "\\'") ~ "'").!.map((c) => (c.length, Charr))
}

case object Variable extends CrystalTokenType("variable") {
  lazy val variable: core.Parser[(Int, Variable.type), Char, String] = P((CharIn('a' to 'z', 'A' to 'Z') | "_").rep(1)).!.map((a) => (a.length, Variable))
}

case object Namespace extends CrystalTokenType("namespace") {
  lazy val ns: core.Parser[(Int, Namespace.type), Char, String] = P("::").!.map((n) => (n.length, Namespace))
}

case object Ended extends CrystalTokenType("eof") {
  lazy val eof: core.Parser[(Int, Ended.type), Char, String] = P(End).!.map((_) => (1, Ended))
}

case object BadParse extends CrystalTokenType("error")