package tech.voicer.voicerapp.infra.api

import tech.voicer.voicerapp.shared.Error

enum class ApiResponseError(val message:String): Error {
  NETWORK_ERROR("Rede não acessível!"),
  REQUEST_TIMEOUT("Requisição não atendida à tempo!"),
  UNAUTHORIZED("Erro ao autenticar o usuário!"),
  SERVER_ERROR("Erro de Backend!"),
  SERIALIZATION("Dados inválidos!"),
  BAD_REQUEST("Requisição inválida!"),
  UNKNOWN("Erro desconhecido!")
}