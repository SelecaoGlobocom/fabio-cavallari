package com.example.datatrackapp.data.repository

import com.example.datatrackapp.data.remoteprovider.TrackApiRemoteProvider
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.utils.Logger.log

class DataTrackRepositoryImpl(
    private val remoteProvider: TrackApiRemoteProvider
): DataTrackRepository {
    override suspend fun trackHit(hit: Hit) {
//        remoteProvider.trackHit(hit)
        println(log(hit))
    }
}