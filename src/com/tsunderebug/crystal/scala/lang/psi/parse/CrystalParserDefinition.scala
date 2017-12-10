package com.tsunderebug.crystal.scala.lang.psi.parse

import com.intellij.lang.{ASTNode, ParserDefinition, PsiParser}
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.tree.{IFileElementType, TokenSet}
import com.intellij.psi.{FileViewProvider, PsiElement, PsiFile}
import com.tsunderebug.crystal.scala.lang.CrystalLanguage
import com.tsunderebug.crystal.scala.lang.file.CrystalFile
import com.tsunderebug.crystal.scala.lang.psi._
import com.tsunderebug.crystal.scala.lang.psi.lex.CrystalLexer

class CrystalParserDefinition extends ParserDefinition {

  override def getFileNodeType: IFileElementType = CrystalParserDefinition.file

  override def createElement(astNode: ASTNode): PsiElement = astNode.getPsi

  override def createFile(fileViewProvider: FileViewProvider): PsiFile = new CrystalFile(fileViewProvider)

  override def getStringLiteralElements: TokenSet = TokenSet.create(Ztring)

  override def spaceExistanceTypeBetweenTokens(astNode: ASTNode, astNode1: ASTNode): ParserDefinition.SpaceRequirements = ParserDefinition.SpaceRequirements.MAY

  override def createLexer(project: Project): Lexer = new CrystalLexer()

  override def getCommentTokens: TokenSet = TokenSet.create(Comment)

  override def getWhitespaceTokens: TokenSet = TokenSet.create(Whitespace)

  override def createParser(project: Project): PsiParser = new CrystalParser

}

object CrystalParserDefinition {

  val file = new IFileElementType(CrystalLanguage)

}
