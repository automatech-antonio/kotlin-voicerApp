package tech.voicer.voicerapp.infra.stt

import org.json.JSONArray
import tech.voicer.voicerapp.infra.stt.shared.Numeral
import tech.voicer.voicerapp.infra.stt.shared.getUnitNumericValues

class NumericGrammar {
  private val numericGrammar: MutableList<Numeral> = mutableListOf()

  companion object {
    @Volatile private var instance: NumericGrammar? = null
    fun getInstance() = instance ?: synchronized(this) {
      instance ?: NumericGrammar().also { instance = it }
    }
  }

  init {
    numericGrammar.addAll(getUnitNumericValues())
  }

  fun getGrammar(): String {
    val numerics = numericGrammar.map { num -> num.text }.toTypedArray()
    return JSONArray(numerics).toString()
  }
}