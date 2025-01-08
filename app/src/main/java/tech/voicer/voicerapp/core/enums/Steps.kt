package tech.voicer.voicerapp.core.enums

enum class Steps(val module: Modules?, val help: String) {
  NULL(null,"em espera"),
  SET_USER(Modules.LOGIN, "informar código do usuário"),
  SET_PASSWORD(Modules.LOGIN, "informar senha do usuário")
}