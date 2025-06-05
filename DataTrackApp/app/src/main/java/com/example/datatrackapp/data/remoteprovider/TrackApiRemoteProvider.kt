package com.example.datatrackapp.data.remoteprovider

import com.example.datatrackapp.domain.model.Hit

interface TrackApiRemoteProvider {
    suspend fun trackHit(hit: Hit) : Boolean
}