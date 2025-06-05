package com.example.datatrackapp.data.repository

interface DataTrackRepository {
    suspend fun trackHit(eventName: String)
}