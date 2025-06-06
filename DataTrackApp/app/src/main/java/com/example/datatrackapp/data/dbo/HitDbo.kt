package com.example.datatrackapp.data.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.domain.model.HitType

@Entity(tableName = "hit")
data class HitDbo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: HitType,
    val name: String,
    val data: Map<String, String>,
    val timestamp: Long
)

fun Hit.asDboModel() = HitDbo(
    type = type,
    name = name,
    data = data,
    timestamp = timestamp
)