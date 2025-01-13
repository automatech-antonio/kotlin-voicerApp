package tech.voicer.voicerapp.database.repositories

import tech.voicer.voicerapp.core.models.Configuration
import tech.voicer.voicerapp.database.dao.ConfigurationDao
import tech.voicer.voicerapp.database.entities.ConfigurationEntity
import tech.voicer.voicerapp.database.enums.ConfigurationResultData
import tech.voicer.voicerapp.database.enums.ConfigurationSearch

class ConfigurationRepository(private val dao: ConfigurationDao) {
  suspend fun save(config: Configuration) {
    val entity = config.toConfigurationEntity()
    dao.save(entity)
  }

  suspend fun getConfig(data: ConfigurationSearch): ConfigurationResultData {
    return dao.findOne(
      data.confService.toString(), data.confDomain.toString(), data.confObject.toString(), data.confName
    )
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