package tech.voicer.voicerapp.infra.api

import io.ktor.http.HttpMethod
import tech.voicer.voicerapp.core.models.Configuration
import tech.voicer.voicerapp.infra.Http
import tech.voicer.voicerapp.shared.Result

class Backend() {
  private val client = Http.getInstance().getClient()

  suspend fun getInfoService(): Result<ApiResponse<ServerInfo>, ApiResponseError> {
    return apiExecute<ServerInfo>(client, ApiRequest(false, HttpMethod.Get, null))
  }
  suspend fun getConfigurations(): Result<ApiResponse<Configuration>, ApiResponseError> {
    return apiExecute(client, ApiRequest(true, HttpMethod.Get, "configurations"))
  }
}