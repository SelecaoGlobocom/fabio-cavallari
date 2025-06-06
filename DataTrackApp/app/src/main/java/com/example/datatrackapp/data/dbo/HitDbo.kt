package com.example.datatrackapp.data.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.datatrackapp.data.dto.HitDto
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.domain.model.HitType

@Entity(tableName = "hit")
data class HitDbo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: HitType,
    val name: String,
    val data: Map<String, String>,
    val timestamp: Long,
    val sent: Boolean = false
)

fun Hit.asDboModel() = HitDbo(
    type = type,
    name = name,
    data = data,
    timestamp = timestamp
)

fun HitDbo.asDtoModel() = HitDto(
    type = type,
    name = name,
    data = data,
    timestamp = timestamp
)

fun List<HitDbo>.asDtoModel() = map { it.asDtoModel() }