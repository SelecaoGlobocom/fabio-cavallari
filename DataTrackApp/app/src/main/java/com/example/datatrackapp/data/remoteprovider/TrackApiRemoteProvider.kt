package com.example.datatrackapp.data.remoteprovider

import com.example.datatrackapp.data.dto.HitDto
import com.example.datatrackapp.data.utils.Result

interface TrackApiRemoteProvider {
    suspend fun trackHit(hitList: List<HitDto>) : Result<Unit>
}