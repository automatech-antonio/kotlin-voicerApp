package tech.voicer.voicerapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
  @PrimaryKey val id: String,
  val name: String,
  @ColumnInfo(name = "integration_code") val integrationCode: String,
  @ColumnInfo(name = "spoken_name") val spokenName: String,
  val speed: Int,
  val token: String,
  @ColumnInfo(name = "expiration_date") val expirationDate: Long
)