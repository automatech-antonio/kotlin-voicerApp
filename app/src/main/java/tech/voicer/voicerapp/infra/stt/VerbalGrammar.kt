package tech.voicer.voicerapp.infra.stt

import org.json.JSONArray
import tech.voicer.voicerapp.infra.stt.shared.VerbalCommands

class VerbalGrammar private constructor(){
  private val verbalGrammar: MutableList<VerbalCommands> = mutableListOf()

  companion object {
    @Volatile private var instance: VerbalGrammar? = null
    fun getInstance() = instance ?: synchronized(this) {
      instance ?: VerbalGrammar().also { instance = it }
    }
  }

  init {
    verbalGrammar.add(VerbalCommands.SYS_START)
    verbalGrammar.add(VerbalCommands.SYS_OUT)
  }

  fun getGrammar(): String {
    val commands = verbalGrammar.map { command -> command.text }.toTypedArray()
    return JSONArray(commands).toString()
  }

  fun isVerbal(data: String): Boolean {
    if (verbalGrammar.isEmpty()) return false
    for (tk in verbalGrammar) {
      if (tk.text == data) return true
    }
    return false
  }
}