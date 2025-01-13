package tech.voicer.voicerapp.domain

import android.app.Activity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import tech.voicer.voicerapp.core.stages.Stage
import tech.voicer.voicerapp.core.stages.Stages
import tech.voicer.voicerapp.core.enums.Applications
import tech.voicer.voicerapp.core.enums.Modules
import tech.voicer.voicerapp.core.enums.Steps
import tech.voicer.voicerapp.database.Db
import tech.voicer.voicerapp.database.enums.ConfigurationSearch
import tech.voicer.voicerapp.database.enums.Domain
import tech.voicer.voicerapp.database.enums.Object
import tech.voicer.voicerapp.database.enums.Service
import tech.voicer.voicerapp.database.repositories.ConfigurationRepository
import tech.voicer.voicerapp.infra.Tts
import tech.voicer.voicerapp.shared.Messages

class LoginUseCase(private val activity: Activity) {
  private val stage = Stages.getInstance()
  private val context = activity.applicationContext
  private val db = Db.getInstance().getDb(context)

  init {
    stage.addStage(Stage(Applications.PICKING, Modules.LOGIN, Steps.SET_USER))

    // Habilita o evento do código do usuário


    MainScope().launch {
      val loginWithPassword = ConfigurationRepository(db.configurationDao()).getConfig(
        ConfigurationSearch(
          Service.PICKING, Domain.DIALOG, Object.SCRIPT, "PASSWORD_ON_LOGIN"
        )
      )
      if (loginWithPassword.booleanVal) {
        // Habilita o evento de senha do usuário
      }
    }


    Tts(activity, Messages.LOGIN_SET_USER.text)
  }
}