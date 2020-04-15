package com.fieldcode.myandroidtemplate.repository

class JokeRepository(private val service: JokeService) : BaseRepository() {

    suspend fun getJoke() = safeApiCall(
        call = { service.getOneJoke() },
        errorMessage = "Error when fetching joke"
    )

}