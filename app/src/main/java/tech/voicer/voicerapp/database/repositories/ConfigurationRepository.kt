package tech.voicer.voicerapp.database.repositories

import tech.voicer.voicerapp.core.models.Configuration
import tech.voicer.voicerapp.database.dao.ConfigurationDao
import tech.voicer.voicerapp.database.entities.ConfigurationEntity

class ConfigurationRepository(private val dao: ConfigurationDao) {
  suspend fun save(config: Configuration) {
    val entity = config.toConfigurationEntity()
    dao.save(entity)
  }

  suspend fun clear() {
    dao.deleteAll()
  }
}

fun Configuration.toConfigurationEntity() = ConfigurationEntity(
  id = this.id,
  serviceDef = this.serviceDef,
  domainDef = this.domainDef,
  objectDef = this.objectDef,
  configDef = this.configDef,
  stringVal = this.stringVal,
  booleanVal = this.booleanVal
)
fun ConfigurationEntity.toConfiguration() = Configuration(
  id = this.id,
  serviceDef = this.serviceDef,
  domainDef = this.domainDef,
  objectDef = this.objectDef,
  configDef = this.configDef,
  stringVal = this.stringVal,
  booleanVal = this.booleanVal
)