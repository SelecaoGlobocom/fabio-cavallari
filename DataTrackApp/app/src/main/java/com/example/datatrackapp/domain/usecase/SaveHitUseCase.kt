package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.data.repository.DataTrackRepository
import com.example.datatrackapp.domain.model.Hit

class SaveHitUseCase(
    private val repository: DataTrackRepository
) {
    suspend operator fun invoke(hit: Hit) {
        repository.saveHit(hit)
    }
}