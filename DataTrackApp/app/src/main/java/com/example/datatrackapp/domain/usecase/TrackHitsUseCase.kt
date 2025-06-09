package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.BuildConfig
import com.example.datatrackapp.domain.model.Hit

class TrackHitsUseCase (
    private val saveHitUseCase: SaveHitUseCase,
    private val sendBatchHitsUseCase: SendBatchHitsUseCase
) {
    suspend operator fun invoke(hit: Hit) {
        saveHitUseCase(hit)
        if (!BuildConfig.WORK_MANAGER_ENABLED) {
            sendBatchHitsUseCase()
        }
    }
}