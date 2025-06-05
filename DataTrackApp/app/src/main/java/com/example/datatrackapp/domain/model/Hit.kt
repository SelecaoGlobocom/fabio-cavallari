package com.example.datatrackapp.domain.model

data class Hit(
    val type: HitType,
    val data: Map<String, String>,
    val timestamp: String,
)

enum class HitType{
    PAGE_VIEW,
    EVENT,
}