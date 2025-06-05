package com.example.datatrackapp.data.remoteprovider

import com.example.datatrackapp.data.client.TrackApiClient

class TrackApiRemoteProviderImpl(
    private val client: TrackApiClient
) : TrackApiRemoteProvider {
    override suspend fun trackHit(eventName: String) : Boolean {
        val response = client.trackHit(eventName)
        return response.isSuccessful
    }
}