package com.fieldcode.myandroidtemplate.repository

import android.util.Log
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Result.Success -> data = result.data
            is Result.Error -> {
                Log.d("ERROR: ", "$errorMessage EXCEPTION: ${result.exception.message}")
            }
        }
        return data
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        val response = call.invoke()
        return if (response.isSuccessful) Result.Success(response.body()!!) else Result.Error(
            IOException("Error Occurred during getting safe Api result: $errorMessage")
        )
    }
}