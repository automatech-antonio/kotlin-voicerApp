package tech.voicer.voicerapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import tech.voicer.voicerapp.database.entities.ConfigurationEntity
import tech.voicer.voicerapp.database.enums.ConfigurationResultData

@Dao
interface ConfigurationDao {
  @Delete
  suspend fun delete(config: ConfigurationEntity)

  @Query("DELETE from ConfigurationEntity")
  suspend fun deleteAll()

  @Query(
    "SELECT string_val as stringVal, boolean_val as booleanVal from ConfigurationEntity where " +
        "service_def = :findService and domain_def = :findDomain and object_def = :findObject " +
        "and config_def = :findConfig"
  )
  suspend fun findOne(
    findService: String,
    findDomain: String,
    findObject: String,
    findConfig: String
  ): ConfigurationResultData

  @Insert
  suspend fun save(config: ConfigurationEntity)
}