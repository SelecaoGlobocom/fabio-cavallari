package com.example.datatrackapp.data.repository

import com.example.datatrackapp.data.dao.HitDao
import com.example.datatrackapp.data.mapper.asDboModel
import com.example.datatrackapp.data.mapper.asDomainModel
import com.example.datatrackapp.data.mapper.asDtoModel
import com.example.datatrackapp.data.remoteprovider.TrackApiRemoteProvider
import com.example.datatrackapp.data.utils.Result
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.plataform.utils.Logger

class DataTrackRepositoryImpl(
    private val remoteProvider: TrackApiRemoteProvider,
    private val dao: HitDao
): DataTrackRepository {
    override suspend fun saveHit(hit: Hit) {
        dao.insertHit(hit.asDboModel())
        Logger.log("Hit ${hit.name} inserted")
    }

    override suspend fun getUnsentHits(): List<Hit> {
        return dao.getUnsentHits().asDomainModel()
    }

    override suspend fun sendPendingHits(hitList: List<Hit>) {
        val result = remoteProvider.trackHit(hitList.asDtoModel())
        if (result is Result.Success) {
            hitList.forEach { hit ->
                hit.id?.let { dao.markHitAsSent(it) }
            }
        }
    }
}