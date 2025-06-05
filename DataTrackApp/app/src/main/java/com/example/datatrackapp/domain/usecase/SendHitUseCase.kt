package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.data.repository.DataTrackRepository
import com.example.datatrackapp.domain.model.Hit

class SendHitUseCase(
    private val repository: DataTrackRepository
) {
    suspend fun trackHit(hit: Hit) {
        repository.trackHit(hit)
    }
}