package com.tsunderebug.crystal.scala.ide

import javax.swing.{JComponent, JLabel}

import com.intellij.ide.util.projectWizard.ModuleWizardStep

class CrystalModuleWizardStep extends ModuleWizardStep {

  override def getComponent: JComponent = {
    new JLabel("uuhhh.wav")
  }

  override def updateDataModel(): Unit = {

  }

}
