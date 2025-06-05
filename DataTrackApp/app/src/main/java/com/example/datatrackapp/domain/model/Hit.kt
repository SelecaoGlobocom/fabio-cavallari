package com.example.datatrackapp.domain.model

data class Hit(
    val type: HitType,
    val name: String,
    val data: Map<String, String> = mapOf(),
    val timestamp: Long = System.currentTimeMillis(),
)

enum class HitType{
    PAGE_VIEW,
    EVENT,
}