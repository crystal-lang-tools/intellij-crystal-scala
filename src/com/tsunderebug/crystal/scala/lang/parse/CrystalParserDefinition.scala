package com.tsunderebug.crystal.scala.lang.parse

import com.intellij.lang.{ASTNode, ParserDefinition, PsiParser}
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.{FileViewProvider, PsiElement, PsiFile}
import com.intellij.psi.tree.{IFileElementType, TokenSet}
import com.tsunderebug.crystal.scala.lang.CrystalLanguage
import com.tsunderebug.crystal.scala.lang.file.CrystalFile

class CrystalParserDefinition extends ParserDefinition {

  override def getFileNodeType: IFileElementType = CrystalParserDefinition.file

  override def createElement(astNode: ASTNode): PsiElement = ???

  override def createFile(fileViewProvider: FileViewProvider): PsiFile = new CrystalFile(fileViewProvider)

  override def getStringLiteralElements: TokenSet = TokenSet.create()

  override def spaceExistanceTypeBetweenTokens(astNode: ASTNode, astNode1: ASTNode): ParserDefinition.SpaceRequirements = ???

  override def createLexer(project: Project): Lexer = new CrystalLexer()

  override def getCommentTokens: TokenSet = ???

  override def createParser(project: Project): PsiParser = ???

}

object CrystalParserDefinition {

  val file = new IFileElementType(CrystalLanguage)

}