package com.example.datatrackapp.data.remoteprovider

import com.example.datatrackapp.data.client.TrackApiClient
import com.example.datatrackapp.data.dto.HitDto
import com.example.datatrackapp.data.utils.Result
import com.example.datatrackapp.data.utils.handleApiResponse
import com.example.datatrackapp.plataform.di.DataTrackDependencyInjection
import com.example.datatrackapp.plataform.utils.Logger

class TrackApiRemoteProviderImpl(
    private val client: TrackApiClient
) : TrackApiRemoteProvider {
    override suspend fun trackHit(hitList: List<HitDto>): Result<Unit> {
        if (DataTrackDependencyInjection.forceSuccess) {
            Logger.log("sent hit success")
            Logger.log("hit list sent: ${hitList.joinToString(separator = ";") { it.name }}")
            return Result.Success(Unit)
        }
        Logger.log("sent hit failure")
        return Result.Error()
        //TODO - remover force success
        val result = handleApiResponse(
            apiCall = { client.trackHit(hitList) },
            mapBody = { }
        )
        return result
    }
}