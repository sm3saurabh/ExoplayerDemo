package dev.saurabhmishra.exoplayersample.utils

import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.Wood
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

suspend fun <T> safeApiCall(
    executionContext: CoroutineContext,
    apiCall: suspend () -> T
): SafeResult<T> {
    return withContext(executionContext) {
        try {
            SafeResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Wood.error(throwable.localizedMessage.orEmpty(), throwable)
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

}

class ApiException(
    val code: Int? = null,
    val errorBodyString: String? = null
) : Exception(errorBodyString)