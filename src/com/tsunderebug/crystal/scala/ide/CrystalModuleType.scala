package com.tsunderebug.crystal.scala.ide

import javax.swing.Icon

import com.intellij.openapi.module.{ModuleType, ModuleTypeManager}
import com.tsunderebug.crystal.scala.CrystalIcons

class CrystalModuleType extends ModuleType[CrystalModuleBuilder](CrystalModuleType.id) {

  override def getName: String = "Crystal Shard"

  override def getDescription: String = "Crystal Shard"

  override def getNodeIcon(b: Boolean): Icon = CrystalIcons.crystal

  override def createModuleBuilder(): CrystalModuleBuilder = new CrystalModuleBuilder

}

object CrystalModuleType {

  val id = "CRYSTAL_MODULE_TYPE"

  def getInstance: CrystalModuleType = {
    ModuleTypeManager.getInstance().findByID(id).asInstanceOf[CrystalModuleType]
  }

}