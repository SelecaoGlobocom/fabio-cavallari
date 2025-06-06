package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.BuildConfig
import com.example.datatrackapp.data.repository.DataTrackRepository
import com.example.datatrackapp.domain.model.Hit

class TrackHitUseCase(
    private val repository: DataTrackRepository
) {
    suspend fun trackHit(hit: Hit) {
        repository.saveHit(hit)
        val unsentHits = repository.getUnsentHits()
        if (unsentHits.size >= BuildConfig.HIT_BATCH_THRESHOLD) {
            repository.sendPendingHits(unsentHits)
        }
    }
}