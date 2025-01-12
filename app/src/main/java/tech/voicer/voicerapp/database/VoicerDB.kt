package tech.voicer.voicerapp.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.voicer.voicerapp.database.dao.ConfigurationDao
import tech.voicer.voicerapp.database.dao.UserDao
import tech.voicer.voicerapp.database.entities.ConfigurationEntity
import tech.voicer.voicerapp.database.entities.UserEntity
import kotlin.concurrent.Volatile

@Database(entities = [UserEntity::class, ConfigurationEntity::class], version = 2, exportSchema = true)
abstract class VoicerDB : RoomDatabase() {
  abstract fun userDao(): UserDao
  abstract fun configurationDao(): ConfigurationDao
}

class Db private constructor() : Application() {

  companion object {
    @Volatile private var instance: Db? = null
    fun getInstance() = instance ?: synchronized(this) {
      instance ?: Db().also { instance = it }
    }
  }

  init {
    super.onCreate()
  }

  fun getDb(context: Context): VoicerDB {
    return Room.databaseBuilder(context, VoicerDB::class.java, "voicer.db").build()
  }
}