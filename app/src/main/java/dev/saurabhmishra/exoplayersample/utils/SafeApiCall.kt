package dev.saurabhmishra.exoplayersample.utils

import android.util.Log
import dev.saurabhmishra.domain.SafeResult
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): SafeResult<T> {
    return try {
            SafeResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Log.e("safeApiCall", throwable.message ?: "")
            when (throwable) {
                is HttpException -> {
                    val code = throwable.response()?.code()
                    val body = throwable.response()?.errorBody()
                    SafeResult.Failure(ApiException(code = code, errorBodyString = body?.string()))
                }
                else -> SafeResult.Failure(Exception(throwable))
            }
        }

}

class ApiException(
    val code: Int? = null,
    val errorBodyString: String? = null
) : Exception(errorBodyString)