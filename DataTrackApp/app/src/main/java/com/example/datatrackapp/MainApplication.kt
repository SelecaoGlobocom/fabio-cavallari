package com.example.datatrackapp

import android.app.Application
import com.example.datatrackapp.di.DataTrackDependencyInjection
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                DataTrackDependencyInjection.networkModules,
                DataTrackDependencyInjection.appModules,
            )
        }
    }
}