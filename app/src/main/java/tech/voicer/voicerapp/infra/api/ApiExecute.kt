package tech.voicer.voicerapp.infra.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import tech.voicer.voicerapp.shared.Result
import java.net.ConnectException

suspend inline fun <reified T> apiExecute(client: HttpClient, data: ApiRequest): Result<ApiResponse<T>, ApiResponseError> {
  val response = try {
    client.request {
      method = data.method
      headers { if (data.headerOrigin) {
        header("vcr_origin", "MOBILE")
      } }
      url(data.path ?: "")
    }
  } catch (e: UnresolvedAddressException) {
    return Result.Error(ApiResponseError.NETWORK_ERROR, e.toString())
  } catch (e: SerializationException) {
    return Result.Error(ApiResponseError.SERIALIZATION, e.toString())
  } catch (e: ConnectException) {
    return Result.Error(ApiResponseError.NETWORK_ERROR, e.toString())
  } catch (e: HttpRequestTimeoutException) {
    return Result.Error(ApiResponseError.REQUEST_TIMEOUT, e.toString())
  }

  return when(response.status.value) {
    in 200..299 -> Result.Success(response.body<ApiResponse<T>>())
    400 -> Result.Error(ApiResponseError.BAD_REQUEST, response.body<ApiResponse<T>>().error)
    401 -> Result.Error(ApiResponseError.UNAUTHORIZED, response.body<ApiResponse<T>>().error)
    408 -> Result.Error(ApiResponseError.REQUEST_TIMEOUT, null)
    in 500..599 -> Result.Error(ApiResponseError.SERVER_ERROR, null)
    else -> Result.Error(ApiResponseError.UNKNOWN, response.status.description)
  }
}
