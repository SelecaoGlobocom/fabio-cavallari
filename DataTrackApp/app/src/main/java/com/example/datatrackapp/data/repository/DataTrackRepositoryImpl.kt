package com.example.datatrackapp.data.repository

import com.example.datatrackapp.data.remoteprovider.TrackApiRemoteProvider

class DataTrackRepositoryImpl(
    private val remoteProvider: TrackApiRemoteProvider
): DataTrackRepository {
    override suspend fun trackHit(eventName: String) {
//        remoteProvider.trackHit(eventName)
        println(">>> $eventName")
    }
}