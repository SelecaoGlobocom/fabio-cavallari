package com.example.datatrackapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.datatrackapp.data.dbo.HitDbo

@Dao
interface HitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHit(hit: HitDbo)

    @Query("SELECT * FROM hit ORDER BY timestamp DESC")
    suspend fun getAllHits(): List<HitDbo>
}