package com.tsunderebug.crystal.scala.ide

import java.io.File

import com.intellij.ide.util.projectWizard.{ModuleBuilder, ModuleWizardStep, WizardContext}
import com.intellij.openapi.Disposable
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableRootModel

class CrystalModuleBuilder extends ModuleBuilder {

  override def setupRootModel(modifiableRootModel: ModifiableRootModel): Unit = {
    val t = System.currentTimeMillis()
    val p = modifiableRootModel.getProject.getBaseDir.getParent.getPath
    val pb = new ProcessBuilder("crystal", "init", "app", modifiableRootModel.getProject.getName, modifiableRootModel.getProject.getName + s"-tmp$t").directory(new File(p)).inheritIO()
    val proc = pb.start()
    proc.waitFor()
    val rd = new File(p)
    val td = new File(rd, modifiableRootModel.getProject.getName + s"-tmp$t")
    td.listFiles().foreach((f) => {
      val ps = f.getAbsolutePath.replaceAllLiterally(modifiableRootModel.getProject.getName + s"-tmp$t", modifiableRootModel.getProject.getName)
      val rf = new File(ps)
      f.renameTo(rf)
    })
    td.delete()
    val ce = modifiableRootModel.addContentEntry(modifiableRootModel.getProject.getBaseDir)
    ce.addSourceFolder(modifiableRootModel.getProject.getBaseDir.findChild("src"), false)
    ce.addSourceFolder(modifiableRootModel.getProject.getBaseDir.findChild("spec"), true)
  }

  override def getCustomOptionsStep(context: WizardContext, parentDisposable: Disposable): ModuleWizardStep = {
    new CrystalModuleWizardStep
  }

  override def getModuleType: ModuleType[_ <: ModuleBuilder] = CrystalModuleType.getInstance

}
