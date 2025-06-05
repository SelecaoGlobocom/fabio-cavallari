package com.example.datatrackapp.di

import android.system.Os.bind
import com.example.datatrackapp.data.client.TrackApiClient
import com.example.datatrackapp.data.remoteprovider.TrackApiRemoteProvider
import com.example.datatrackapp.data.remoteprovider.TrackApiRemoteProviderImpl
import com.example.datatrackapp.data.repository.DataTrackRepository
import com.example.datatrackapp.data.repository.DataTrackRepositoryImpl
import com.example.datatrackapp.domain.usecase.SendHitUseCase
import com.example.datatrackapp.presentation.viewmodel.HomeScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object DataTrackDependencyInjection {
    val networkModules = module {
        factory<OkHttpClient> {
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
        factory<TrackApiClient> {
            Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8080/")
                .client(get())
                .build()
                .create(TrackApiClient::class.java)
        }
    }

    val appModules = module {
        factoryOf(::TrackApiRemoteProviderImpl) { bind<TrackApiRemoteProvider>() }
        factoryOf(::DataTrackRepositoryImpl) { bind<DataTrackRepository>() }
        factoryOf(::SendHitUseCase) { bind<SendHitUseCase>() }
        viewModelOf(::HomeScreenViewModel)
    }
}