package com.example.datatrackapp.data.client

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TrackApiClient {
    @POST("/")
    fun trackHit(
        @Body eventName: String
    ): Response<Unit>
}