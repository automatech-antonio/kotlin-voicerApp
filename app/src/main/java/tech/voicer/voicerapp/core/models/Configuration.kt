package tech.voicer.voicerapp.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
  val id: String,
  @SerialName("service") val serviceDef: String,
  @SerialName("domain") val domainDef: String,
  @SerialName("object") val objectDef: String,
  @SerialName("config") val configDef: String,
  @SerialName("stringValue") val stringVal: String,
  @SerialName("boolValue") val booleanVal: Boolean
)