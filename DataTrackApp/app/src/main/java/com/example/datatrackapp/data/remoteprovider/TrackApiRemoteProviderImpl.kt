package com.example.datatrackapp.data.remoteprovider

import com.example.datatrackapp.data.client.TrackApiClient
import com.example.datatrackapp.data.dto.HitDto
import com.example.datatrackapp.di.DataTrackDependencyInjection
import com.example.datatrackapp.utils.Logger

class TrackApiRemoteProviderImpl(
    private val client: TrackApiClient
) : TrackApiRemoteProvider {
    override suspend fun trackHit(hitList: List<HitDto>): Boolean {
        if (DataTrackDependencyInjection.forceSuccess) {
            Logger.log("sent hit success")
            Logger.log("hit list sent: ${hitList.joinToString(separator = ";") { it.name }}")
            return true
        }
        Logger.log("sent hit failure")
        return false
        //TODO - remover force success
        val response = client.trackHit(hitList)
        return response.isSuccessful
    }
}