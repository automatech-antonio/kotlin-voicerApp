package tech.voicer.voicerapp.core

import tech.voicer.voicerapp.core.enums.VerbalCommands
import java.lang.invoke.MethodHandle

data class VerbalEvent(val activeStage: StageVariation, val command: VerbalCommands, val fnAction: () -> Unit)
data class NumericEvent(val activeStage: StageVariation, val fnAction: Function<Unit>)

class EventActions private constructor() {
  private var stage = Stages.getInstance()
  private var verbalEventActions: MutableList<VerbalEvent> = mutableListOf()
  private var numericEventActions: MutableList<NumericEvent> = mutableListOf()

  companion object {
    @Volatile private var instance: EventActions? = null
    fun getInstance() = instance ?: synchronized(this) {
      instance ?: EventActions().also { instance = it }
    }
  }

  private fun hasVerbalEvent(event: VerbalEvent): Boolean {
    return verbalEventActions.contains(event)
  }

  fun addVerbalEvent(data: VerbalEvent) {
    if (!hasVerbalEvent(data)) {
      val pos = if(verbalEventActions.isEmpty()) 0 else verbalEventActions.size -1
      verbalEventActions.add(pos, data)
    }
  }
  fun hasValidVerbalEvent(): Boolean {
    if (verbalEventActions.isEmpty()) return false
    for (ev in verbalEventActions) {
      if (stage.match(ev.activeStage)) return true
    }
    return false
  }
  fun hasValidNumericEvent(): Boolean {
    if (numericEventActions.isEmpty()) return false
    for (ev in numericEventActions) {
      if (stage.match(ev.activeStage)) return true
    }
    return false
  }
  fun resolveVerbal(data: String) {
    for (ev in verbalEventActions) {
      if (stage.match(ev.activeStage) && ev.command.text == data) {
        ev.fnAction()
        return
      }
    }
  }
}