package tech.voicer.voicerapp.database.enums

enum class Service {
  PICKING
}

enum class Domain {
  DIALOG,
  TTS
}

enum class Object {
  SCRIPT,
  QUANTITY,
  COMMANDS,
  ADDRESS,
  VERIFICATION,
  CONTAINER
}

data class ConfigurationSearch(
  val confService: Service,
  val confDomain: Domain,
  val confObject: Object,
  val confName: String
)

data class ConfigurationResultData(
  val stringVal: String,
  val booleanVal: Boolean
)