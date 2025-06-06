package com.example.datatrackapp.di

import androidx.room.Room
import com.example.datatrackapp.data.client.TrackApiClient
import com.example.datatrackapp.data.dao.HitDao
import com.example.datatrackapp.data.database.DataTrackDatabase
import com.example.datatrackapp.data.remoteprovider.TrackApiRemoteProvider
import com.example.datatrackapp.data.remoteprovider.TrackApiRemoteProviderImpl
import com.example.datatrackapp.data.repository.DataTrackRepository
import com.example.datatrackapp.data.repository.DataTrackRepositoryImpl
import com.example.datatrackapp.domain.usecase.TrackHitUseCase
import com.example.datatrackapp.presentation.viewmodel.ChannelScreenViewModel
import com.example.datatrackapp.presentation.viewmodel.HomeScreenViewModel
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataTrackDependencyInjection {
    val databaseModules = module {
        single<DataTrackDatabase> {
            Room.databaseBuilder(
                get(),
                DataTrackDatabase::class.java,
                "hit-db"
            )
                .fallbackToDestructiveMigration(true)
                .build()
        }
        single<HitDao> { get<DataTrackDatabase>().hitDao() }
    }

    val networkModules = module {
        single<OkHttpClient> {
            OkHttpClient.Builder()
                .dispatcher(Dispatcher().apply {
                    maxRequests = 1
                    maxRequestsPerHost = 1
                })
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
        single<TrackApiClient> {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:5000/")
                .client(get())
                .build()
                .create(TrackApiClient::class.java)
        }
    }

    val appModules = module {
        factoryOf(::TrackApiRemoteProviderImpl) { bind<TrackApiRemoteProvider>() }

        factoryOf(::DataTrackRepositoryImpl) { bind<DataTrackRepository>() }

        factoryOf(::TrackHitUseCase) { bind<TrackHitUseCase>() }

        viewModelOf(::HomeScreenViewModel)
        viewModelOf(::ChannelScreenViewModel)
    }
}