package com.example.datatrackapp.data.repository

import com.example.datatrackapp.domain.model.Hit

interface DataTrackRepository {
    suspend fun saveHit(hit: Hit)
    suspend fun getUnsentHits(): List<Hit>
    suspend fun sendPendingHits(hitList: List<Hit>)
}