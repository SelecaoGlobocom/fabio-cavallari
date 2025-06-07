package com.example.datatrackapp.utils

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.datatrackapp.DataTrackerWorker
import org.koin.core.component.KoinComponent
import java.util.concurrent.TimeUnit

object WorkManagerHelper: KoinComponent {
    fun scheduleWorkManager(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<DataTrackerWorker>(
            30, TimeUnit.SECONDS
        ).build()

        WorkManager.getInstance(context).apply {
            enqueueUniquePeriodicWork(
                "DataTrackWorkerNew",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }
    }
}