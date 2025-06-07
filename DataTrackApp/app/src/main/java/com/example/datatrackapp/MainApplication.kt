package com.example.datatrackapp

import android.app.Application
import com.example.datatrackapp.di.DataTrackDependencyInjection
import com.example.datatrackapp.utils.WorkManagerHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class MainApplication : Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            workManagerFactory()
            modules(
                DataTrackDependencyInjection.workerModules,
                DataTrackDependencyInjection.databaseModules,
                DataTrackDependencyInjection.networkModules,
                DataTrackDependencyInjection.appModules,
            )
        }

        WorkManagerHelper.scheduleWorkManager(this)
    }

}