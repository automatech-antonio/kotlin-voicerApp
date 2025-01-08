package tech.voicer.voicerapp.infra.api

import kotlinx.serialization.Serializable

@Serializable
data class ApiResult<T>(
  val result: Boolean?,
  val recordset: T?,
  val recordsets: List<T>?,
  val error: String?
)

@Serializable
data class ServerInfo(val source: String, val version: String)