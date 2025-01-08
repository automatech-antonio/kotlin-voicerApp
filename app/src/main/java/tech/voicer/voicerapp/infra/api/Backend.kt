package tech.voicer.voicerapp.infra.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import tech.voicer.voicerapp.shared.ApiError
import tech.voicer.voicerapp.shared.Result
import java.net.ConnectException

class Backend(private val client: HttpClient) {
  suspend fun infoService(): Result<ApiResult<ServerInfo>, ApiError> {
    val response = try {
      client.get(){ timeout { requestTimeoutMillis = 5000 }}
    } catch (e: UnresolvedAddressException) {
      return Result.Error(ApiError.NETWORK_ERROR)
    } catch (e: SerializationException) {
      return Result.Error(ApiError.SERIALIZATION)
    } catch (e: ConnectException) {
      return Result.Error(ApiError.NETWORK_ERROR)
    } catch (e: HttpRequestTimeoutException) {
      return Result.Error(ApiError.REQUEST_TIMEOUT)
    }

    return when(response.status.value) {
      in 200..299 -> Result.Success(response.body<ApiResult<ServerInfo>>())
      401 -> Result.Error(ApiError.UNAUTHORIZED)
      408 -> Result.Error(ApiError.REQUEST_TIMEOUT)
      in 500..599 -> Result.Error(ApiError.SERVER_ERROR)
      else -> Result.Error(ApiError.UNKNOWN)
    }
  }
}