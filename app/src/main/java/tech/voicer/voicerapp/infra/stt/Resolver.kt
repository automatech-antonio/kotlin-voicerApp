package tech.voicer.voicerapp.infra.stt

import tech.voicer.voicerapp.core.EventActions

class Resolver {
  private val events = EventActions.getInstance()

  private fun sanitize(data: String): String {
    return data.trim().lowercase()
  }
//  private fun tokenize

  fun evaluate(data: String) {
    val type = if(VerbalGrammar().isVerbal(data)) ResolverType.VERBAL else ResolverType.NUMERIC
    if (type == ResolverType.VERBAL) {
      if (!events.hasValidVerbalEvent()) return
      events.resolveVerbal(sanitize(data))
    }
  }
}