package tech.voicer.voicerapp.domain

import android.app.Activity
import tech.voicer.voicerapp.core.stages.Stage
import tech.voicer.voicerapp.core.stages.Stages
import tech.voicer.voicerapp.core.enums.Applications
import tech.voicer.voicerapp.core.enums.Modules
import tech.voicer.voicerapp.core.enums.Steps
import tech.voicer.voicerapp.infra.Tts
import tech.voicer.voicerapp.shared.Messages

class LoginUseCase(private val activity: Activity) {
  private val stage = Stages.getInstance()

  init {
    stage.addStage(Stage(Applications.PICKING, Modules.LOGIN, Steps.SET_USER))
    Tts(activity, Messages.LOGIN_SET_USER.text)
  }
}