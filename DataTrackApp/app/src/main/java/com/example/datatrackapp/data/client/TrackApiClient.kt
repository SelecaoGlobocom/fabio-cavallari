package com.example.datatrackapp.data.client

import com.example.datatrackapp.data.dto.HitDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TrackApiClient {
    @POST("/")
    suspend fun trackHit(
        @Body hit: HitDto
    ): Response<Unit>
}