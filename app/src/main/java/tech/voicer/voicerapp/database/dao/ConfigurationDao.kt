package tech.voicer.voicerapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import tech.voicer.voicerapp.database.entities.ConfigurationEntity

@Dao
interface ConfigurationDao {
  @Delete suspend fun delete(config: ConfigurationEntity)

  @Insert suspend fun save(config: ConfigurationEntity)
}