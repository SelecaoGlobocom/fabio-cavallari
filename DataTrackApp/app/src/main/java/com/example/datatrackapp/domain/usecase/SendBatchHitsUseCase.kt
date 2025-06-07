package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.BuildConfig
import com.example.datatrackapp.data.repository.DataTrackRepository

class SendBatchHitsUseCase(
    private val repository: DataTrackRepository
) {
    suspend operator fun invoke() {
        val unsentHits = repository.getUnsentHits()
        if (unsentHits.size >= BuildConfig.HIT_BATCH_THRESHOLD) {
            repository.sendPendingHits(unsentHits)
        }
    }
}