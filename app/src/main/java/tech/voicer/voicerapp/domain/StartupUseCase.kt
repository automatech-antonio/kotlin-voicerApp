package tech.voicer.voicerapp.domain

import android.app.Activity
import android.util.Log
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import tech.voicer.voicerapp.core.events.EventActions
import tech.voicer.voicerapp.core.stages.Stage
import tech.voicer.voicerapp.core.stages.StageVariation
import tech.voicer.voicerapp.core.stages.Stages
import tech.voicer.voicerapp.core.events.VerbalEvent
import tech.voicer.voicerapp.core.enums.Applications
import tech.voicer.voicerapp.core.enums.Modules
import tech.voicer.voicerapp.core.enums.StageVariationType
import tech.voicer.voicerapp.core.enums.Steps
import tech.voicer.voicerapp.core.enums.VerbalCommands
import tech.voicer.voicerapp.infra.api.Backend
import tech.voicer.voicerapp.shared.onError
import tech.voicer.voicerapp.shared.onSuccess

class StartupUseCase(private val activity: Activity) {
  private val stage = Stages.getInstance()
  private val event = EventActions.getInstance()

  init {
    val basicStage = Stage(Applications.PICKING, Modules.STARTUP, Steps.NULL)
    val basicVariation = StageVariation(StageVariationType.IS, basicStage)
    // Carrega as configurações referente ao aplicativo lançado (Atualmente apenas PICKING)
    MainScope().launch {
      Backend().getConfigurations()
        .onSuccess { res -> Log.d("Configurations", res.recordsets.toString()) }
        .onError { err, msg -> Log.e("Configurations", err.toString() + " - " + msg.toString()) }
    }
    stage.addStage(basicStage)
    event.addVerbalEvent(
      VerbalEvent(basicVariation, VerbalCommands.SYS_START, ::start)
    )
  }

  private fun start() {
    // Verificar se existe algum usuário salvo com um token válido
    // Se existir ->
  }
}