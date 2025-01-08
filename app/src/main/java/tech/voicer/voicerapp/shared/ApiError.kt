package tech.voicer.voicerapp.shared

enum class ApiError: Error {
  NETWORK_ERROR,
  REQUEST_TIMEOUT,
  UNAUTHORIZED,
  SERVER_ERROR,
  SERIALIZATION,
  UNKNOWN
}