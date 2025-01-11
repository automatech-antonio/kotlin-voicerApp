package tech.voicer.voicerapp.core.stages

import tech.voicer.voicerapp.core.enums.Applications
import tech.voicer.voicerapp.core.enums.Modules
import tech.voicer.voicerapp.core.enums.StageVariationType
import tech.voicer.voicerapp.core.enums.Steps

data class Stage(val app: Applications, val module: Modules, val step: Steps)
data class StageVariation(val type: StageVariationType, val stage: Stage)

class Stages private constructor() {
  private val pickingBaseStage = Stage(Applications.PICKING, Modules.NULL, Steps.NULL)
  private var stageList: MutableList<Stage> = mutableListOf(pickingBaseStage)

  companion object {
    @Volatile private var instance: Stages? = null

    fun getInstance() = instance ?: synchronized(this) {
      instance  ?: Stages().also { instance = it }
    }
  }

  private fun hasStage(stage: Stage): Boolean {
    return stageList.contains(stage)
  }

  fun getActualStage(): Stage {
    if (stageList.size == 0) {
      return pickingBaseStage
    }
    return stageList[stageList.size - 1]
  }

  fun addStage(stage: Stage) {
    if (!hasStage(stage)) {
      val pos = if (stageList.isEmpty()) 0 else stageList.size -1
      stageList.add(pos, stage)
    }
  }

  fun match(compare: StageVariation): Boolean {
    if (stageList.isEmpty()) return false
    if (compare.type == StageVariationType.IS && compare.stage == getActualStage()) return true
    return stageList.contains(compare.stage)
  }
}