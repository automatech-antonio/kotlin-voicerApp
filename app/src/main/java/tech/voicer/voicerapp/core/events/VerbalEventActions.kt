package tech.voicer.voicerapp.core.events

class VerbalEventActions private constructor() {

  companion object {
    @Volatile private var instance: VerbalEventActions? = null

    fun getInstance() = instance ?: synchronized(this) {
      instance ?: VerbalEventActions().also { instance = it }
    }
  }


}