package tech.voicer.voicerapp.infra

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import tech.voicer.voicerapp.BuildConfig

class Http private constructor(){
  private var client: HttpClient = HttpClient() {
    install(Logging) {
      level = LogLevel.ALL
    }
    install(HttpTimeout)
    install(ContentNegotiation) {
      json( json = Json{ ignoreUnknownKeys = true } )
    }
    defaultRequest { host = BuildConfig.BASE_URL }
  }

  companion object {
    @Volatile private var instance: Http? = null
    fun getInstance() = instance ?: synchronized(this) {
      instance ?: Http().also { instance = it }
    }
  }

  fun getClient(): HttpClient {
    return client
  }

}