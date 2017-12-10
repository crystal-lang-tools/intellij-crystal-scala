package com.tsunderebug.crystal.scala.lang.psi.highlight

import com.intellij.openapi.fileTypes.{SyntaxHighlighter, SyntaxHighlighterFactory}
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class CrystalSyntaxHighlighterFactory extends SyntaxHighlighterFactory {

  override def getSyntaxHighlighter(project: Project, virtualFile: VirtualFile): SyntaxHighlighter = new CrystalSyntaxHighlighter

}
