package com.example.githubpulls

import retrofit2.Response
import com.example.githubpulls.data.Result

/** responsible for handling the API response covering all the cases(success, failure and error) */
abstract class BaseApiSource {

    suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return if (body != null) {
                    Result.success(body)
                } else {
                    Result.error(Throwable("Backend response is null"))
                }
            }
            return error(
                ApiException(
                    responseCode = response.code(),
                    errorResponse = response.errorBody()?.toString()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            //can be extended for handling specific exceptions
            val errorBody = ApiException(
                404,
                "API Failed"
            )

            return error(errorBody)
        }
    }

    private fun <T> error(error: Throwable): Result<T> {
        return Result.error(error)
    }


}

class ApiException(
    val responseCode: Int,
    val errorResponse: String?
) : Exception()
