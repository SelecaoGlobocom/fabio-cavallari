package com.example.datatrackapp.plataform.workmanager

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.datatrackapp.BuildConfig
import org.koin.core.component.KoinComponent
import java.util.concurrent.TimeUnit

object WorkManagerHelper: KoinComponent {
    fun scheduleWorkManager(context: Context) {
        if (!BuildConfig.WORK_MANAGER_ENABLED) {
            return
        }
        val workRequest = PeriodicWorkRequestBuilder<DataTrackerWorker>(
            10, TimeUnit.MINUTES
        ).build()

        WorkManager.Companion.getInstance(context).apply {
            enqueueUniquePeriodicWork(
                "DataTrackWorkerNew",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }
    }
}