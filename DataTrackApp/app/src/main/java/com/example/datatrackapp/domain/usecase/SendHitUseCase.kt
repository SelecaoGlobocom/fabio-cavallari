package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.data.repository.DataTrackRepository

class SendHitUseCase(
    private val repository: DataTrackRepository
) {
    suspend fun trackHit(eventName: String) {
        repository.trackHit(eventName)
    }
}