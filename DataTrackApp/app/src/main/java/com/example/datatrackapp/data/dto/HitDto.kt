package com.example.datatrackapp.data.dto

import com.example.datatrackapp.domain.model.HitType

data class HitDto(
    val type: HitType,
    val name: String,
    val data: Map<String, String> = mapOf(),
    val timestamp: Long = System.currentTimeMillis(),
)
