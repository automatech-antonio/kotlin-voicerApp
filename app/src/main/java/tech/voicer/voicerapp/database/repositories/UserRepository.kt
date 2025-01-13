package tech.voicer.voicerapp.database.repositories

import tech.voicer.voicerapp.core.models.User
import tech.voicer.voicerapp.database.dao.UserDao
import tech.voicer.voicerapp.database.entities.UserEntity

class UserRepository(private val dao: UserDao) {

  suspend fun getUser(): User? {
    val currentUser = dao.find()
    return currentUser?.toUser()
  }

  suspend fun save(user: User) {
    val entity = user.toUserEntity()
    dao.save(entity)
  }
}

fun User.toUserEntity() = UserEntity(
  id = this.id,
  name = this.name,
  spokenName = this.spokenName,
  speed = this.speed,
  token = this.token,
  expirationDate = this.expirationDate,
  integrationCode = "ND"
)

fun UserEntity.toUser() = User(
  id = this.id,
  name = this.name,
  spokenName = this.spokenName,
  speed = this.speed,
  token = this.token,
  expirationDate = this.expirationDate
)