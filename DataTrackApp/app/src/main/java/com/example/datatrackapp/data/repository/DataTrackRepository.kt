package com.example.datatrackapp.data.repository

import com.example.datatrackapp.domain.model.Hit

interface DataTrackRepository {
    suspend fun trackHit(hit: Hit)
    suspend fun updateHitList()
}