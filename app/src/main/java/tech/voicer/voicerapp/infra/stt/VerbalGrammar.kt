package tech.voicer.voicerapp.infra.stt

import org.json.JSONArray
import tech.voicer.voicerapp.core.enums.VerbalCommands

class VerbalGrammar {
  private val verbalGrammar: MutableList<VerbalCommands> = mutableListOf()

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