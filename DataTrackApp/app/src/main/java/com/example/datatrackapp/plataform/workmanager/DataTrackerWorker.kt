package com.example.datatrackapp.plataform.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.datatrackapp.domain.usecase.SendBatchHitsUseCase
import com.example.datatrackapp.plataform.utils.Logger

class DataTrackerWorker(
    appContext: Context,
    params: WorkerParameters,
    private val sendBatchHitsUseCase: SendBatchHitsUseCase
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            sendBatchHitsUseCase(workManagerCall = true)
            Result.success()
        } catch (e: Exception) {
            Logger.log("work error: " + e.message.toString())
            Result.retry()
        }
    }
}