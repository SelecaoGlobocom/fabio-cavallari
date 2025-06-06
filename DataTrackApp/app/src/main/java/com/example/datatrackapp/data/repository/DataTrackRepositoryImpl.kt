package com.example.datatrackapp.data.repository

import com.example.datatrackapp.BuildConfig
import com.example.datatrackapp.data.dao.HitDao
import com.example.datatrackapp.data.dbo.asDboModel
import com.example.datatrackapp.data.dbo.asDtoModel
import com.example.datatrackapp.data.remoteprovider.TrackApiRemoteProvider
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.utils.Logger

class DataTrackRepositoryImpl(
    private val remoteProvider: TrackApiRemoteProvider,
    private val dao: HitDao
): DataTrackRepository {
    override suspend fun trackHit(hit: Hit) {
        saveHit(hit)
        val unsentHits = dao.getUnsentHits()
        if (unsentHits.size >= BuildConfig.HIT_BATCH_THRESHOLD) {
            updateHitList()
        }
    }

    override suspend fun updateHitList() {
        try {
            val unsentHitDboList = dao.getUnsentHits()
            val hitDtoList = unsentHitDboList.asDtoModel()
            val sendHitListSuccess = remoteProvider.trackHit(hitDtoList)
            unsentHitDboList.forEach { hitDbo ->
                if (sendHitListSuccess) {
                    dao.markHitAsSent(hitDbo.id)
                }
            }
        } catch (e: Exception) {
            Logger.log(e.message)
        }
    }


    suspend fun saveHit(hit: Hit) {
        val hitDbo = hit.asDboModel()
        dao.insertHit(hitDbo)
        Logger.log("Hit ${hitDbo.name} inserted")
    }
}