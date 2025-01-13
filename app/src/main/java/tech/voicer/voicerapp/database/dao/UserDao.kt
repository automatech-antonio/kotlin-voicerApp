package tech.voicer.voicerapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tech.voicer.voicerapp.database.entities.UserEntity

@Dao
interface UserDao {
  @Query("SELECT * FROM UserEntity")
  suspend fun find(): UserEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun save(user: UserEntity)

  @Delete
  suspend fun delete(user: UserEntity)

}