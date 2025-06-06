package com.example.datatrackapp.domain.model

data class Hit(
    val id: Int? = null,
    val type: HitType,
    val name: String,
    val data: Map<String, String> = mapOf(),
    val timestamp: Long = System.currentTimeMillis(),
    val sent: Boolean = false,
)

enum class HitType {
    PAGE_VIEW,
    EVENT,
}