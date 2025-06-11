package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.data.repository.DataTrackRepository

class SendBatchHitsUseCase(
    private val batchTrashHold: Int,
    private val repository: DataTrackRepository,
) {
    suspend operator fun invoke(workManagerCall: Boolean = false) {
        val unsentHits = repository.getUnsentHits()
        if (workManagerCall && unsentHits.isNotEmpty()) {
            repository.sendPendingHits(unsentHits)
            return
        }
        if (unsentHits.size >= batchTrashHold) {
            repository.sendPendingHits(unsentHits)
        }
    }
}