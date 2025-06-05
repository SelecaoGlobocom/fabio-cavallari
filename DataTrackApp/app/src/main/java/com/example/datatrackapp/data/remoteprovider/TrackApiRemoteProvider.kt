package com.example.datatrackapp.data.remoteprovider

interface TrackApiRemoteProvider {
    suspend fun trackHit(eventName: String) : Boolean
}