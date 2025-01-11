package tech.voicer.voicerapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConfigurationEntity(
  @PrimaryKey val id: String,
  @ColumnInfo(name = "service_def") val serviceDef: String,
  @ColumnInfo(name = "domain_def") val domainDef: String,
  @ColumnInfo(name = "object_def") val objectDef: String,
  @ColumnInfo(name = "config_def") val configDef: String,
  @ColumnInfo(name = "string_val") val stringVal: String,
  @ColumnInfo(name = "boolean_val") val booleanVal: Boolean,
)