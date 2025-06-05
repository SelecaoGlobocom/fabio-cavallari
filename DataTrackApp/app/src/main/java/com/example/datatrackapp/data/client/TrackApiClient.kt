package com.example.datatrackapp.data.client

import com.example.datatrackapp.domain.model.Hit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TrackApiClient {
    @POST("/")
    fun trackHit(
        @Body hit: Hit
    ): Response<Unit>
}