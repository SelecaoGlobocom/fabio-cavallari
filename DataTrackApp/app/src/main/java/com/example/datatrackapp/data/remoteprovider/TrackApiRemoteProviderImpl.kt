package com.example.datatrackapp.data.remoteprovider

import com.example.datatrackapp.data.client.TrackApiClient
import com.example.datatrackapp.data.dto.HitDto
import com.example.datatrackapp.data.utils.Result
import com.example.datatrackapp.data.utils.handleApiResponse

class TrackApiRemoteProviderImpl(
    private val client: TrackApiClient
) : TrackApiRemoteProvider {
    override suspend fun trackHit(hitList: List<HitDto>): Result<Unit> {
        val result = handleApiResponse(
            apiCall = { client.trackHit(hitList) },
            mapBody = { }
        )
        return result
    }
}