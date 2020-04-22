package com.fieldcode.myandroidtemplate.repository.joke

import com.fieldcode.myandroidtemplate.model.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokeService {

    @GET("random/1")
    suspend fun getOneJoke(): Response<JokeResponse>
}