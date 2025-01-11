package tech.voicer.voicerapp.infra.api

import io.ktor.http.HttpMethod

data class ApiRequest(
  val headerOrigin: Boolean,
  val method: HttpMethod,
  val path: String?
)

