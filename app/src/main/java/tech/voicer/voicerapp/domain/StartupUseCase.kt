package tech.voicer.voicerapp.domain

import android.app.Activity
import android.util.Log
import tech.voicer.voicerapp.core.EventActions
import tech.voicer.voicerapp.core.Stage
import tech.voicer.voicerapp.core.StageVariation
import tech.voicer.voicerapp.core.Stages
import tech.voicer.voicerapp.core.VerbalEvent
import tech.voicer.voicerapp.core.enums.Applications
import tech.voicer.voicerapp.core.enums.Modules
import tech.voicer.voicerapp.core.enums.StageVariationType
import tech.voicer.voicerapp.core.enums.Steps
import tech.voicer.voicerapp.core.enums.VerbalCommands

class StartupUseCase(private val activity: Activity) {
  private val stage = Stages.getInstance()
  private val event = EventActions.getInstance()

  init {
    val basicStage = Stage(Applications.PICKING, Modules.STARTUP, Steps.NULL)
    val basicVariation = StageVariation(StageVariationType.IS, basicStage)

    stage.addStage(basicStage)
    event.addVerbalEvent(
      VerbalEvent(basicVariation, VerbalCommands.SYS_START, ::start)
    )
  }

  private fun start() {
    Log.d("StartupUseCase", "Chamou o comando para inicializar a aplicação!")
  }
}