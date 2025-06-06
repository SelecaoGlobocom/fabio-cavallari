package com.example.datatrackapp.data.remoteprovider

import com.example.datatrackapp.data.dto.HitDto

interface TrackApiRemoteProvider {
    suspend fun trackHit(hit: HitDto) : Boolean
}