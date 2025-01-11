package tech.voicer.voicerapp.shared

typealias EmptyResult<E> = Result<Unit, E>

sealed interface Result<out D, out E: Error> {
  data class Success<out D>(val data: D): Result<D, Nothing>
  data class Error<out E: tech.voicer.voicerapp.shared.Error>(val error: E, val message: String?): Result<Nothing, E>
}
inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
  return when(this) {
    is Result.Error -> Result.Error(error, message)
    is Result.Success -> Result.Success(map(data))
  }
}
inline fun <T, E: Error> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
  return when(this) {
    is Result.Error -> this
    is Result.Success -> {
      action(data)
      this
    }
  }
}
inline fun <T, E: Error> Result<T, E>.onError(action: (E, message: String?) -> Unit): Result<T, E> {
  return when(this) {
    is Result.Error -> {
      action(error, message)
      this
    }
    is Result.Success -> this
  }
}


fun <T, E: Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
  return map {  }
}
