package com.example.datatrackapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.datatrackapp.data.dao.HitDao
import com.example.datatrackapp.data.dbo.HitDbo
import com.example.datatrackapp.utils.Converters

@Database(entities = [HitDbo::class], version = 3)
@TypeConverters(Converters::class)
abstract class DataTrackDatabase : RoomDatabase() {
    abstract fun hitDao(): HitDao
}