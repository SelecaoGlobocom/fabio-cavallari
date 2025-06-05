package com.example.datatrackapp.data.remoteprovider

import com.example.datatrackapp.data.client.TrackApiClient
import com.example.datatrackapp.domain.model.Hit

class TrackApiRemoteProviderImpl(
    private val client: TrackApiClient
) : TrackApiRemoteProvider {
    override suspend fun trackHit(hit: Hit): Boolean {
        val response = client.trackHit(hit)
        return response.isSuccessful
    }
}