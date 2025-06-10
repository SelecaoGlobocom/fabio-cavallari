package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.domain.model.Hit

class TrackHitsUseCase (
    private val workManagerEnabled: Boolean,
    private val saveHitUseCase: SaveHitUseCase,
    private val sendBatchHitsUseCase: SendBatchHitsUseCase
) {
    suspend operator fun invoke(hit: Hit) {
        saveHitUseCase(hit)
        if (!workManagerEnabled) {
            sendBatchHitsUseCase()
        }
    }
}