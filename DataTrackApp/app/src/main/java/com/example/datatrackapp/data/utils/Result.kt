package com.example.datatrackapp.data.utils

import retrofit2.Response

sealed class Result<out R> {
    class Success<out T>(val data: T) : Result<T>()
    open class Error(
        val message: String? = null,
        val errorCode: String? = null,
    ) : Result<Nothing>()
}

suspend fun <T, R> handleApiResponse(
    apiCall: suspend () -> Response<T>,
    mapBody: (T) -> R
): Result<R> {
    try {
        val response = apiCall()
        return if (response.isSuccessful && response.body() != null) {
            Result.Success(mapBody(response.body()!!))
        } else {
            Result.Error(
                message = response.message() ?: "Unknown Error",
                errorCode = response.code().toString()
            )
        }
    } catch (e: Exception) {
        return Result.Error(e.message ?: "Unknown Error")
    }
}