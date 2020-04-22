package com.fieldcode.myandroidtemplate.repository.joke

import com.fieldcode.myandroidtemplate.repository.BaseRepository

class JokeRepository(private val service: JokeService) : BaseRepository() {

    suspend fun getJoke() = safeApiCall(
        call = { service.getOneJoke() },
        errorMessage = "Error when fetching joke"
    )

}