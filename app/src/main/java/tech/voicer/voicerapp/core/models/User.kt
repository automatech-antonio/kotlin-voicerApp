package tech.voicer.voicerapp.core.models

data class User(
  val id: String,
  val name: String,
  val spokenName: String,
  val speed: Int,
  val token: String,
  val expirationDate: Long
)
