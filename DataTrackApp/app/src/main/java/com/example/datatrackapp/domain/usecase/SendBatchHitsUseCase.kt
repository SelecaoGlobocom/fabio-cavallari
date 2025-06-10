package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.data.repository.DataTrackRepository

class SendBatchHitsUseCase(
    private val batchTrashHold: Int,
    private val repository: DataTrackRepository,
) {
    suspend operator fun invoke() {
        val unsentHits = repository.getUnsentHits()
        if (unsentHits.size >= batchTrashHold) {
            repository.sendPendingHits(unsentHits)
        }
    }
}