package com.example.datatrackapp.data.mapper

import com.example.datatrackapp.data.dbo.HitDbo
import com.example.datatrackapp.data.dto.HitDto
import com.example.datatrackapp.domain.model.Hit

fun Hit.asDboModel() = HitDbo(
    type = type,
    name = name,
    data = data,
    timestamp = timestamp
)

fun Hit.asDtoModel() = HitDto(
    type = type,
    name = name,
    data = data,
    timestamp = timestamp
)

fun List<Hit>.asDtoModel() = map { it.asDtoModel() }

fun HitDbo.asDomainModel() = Hit(
    id = id,
    type = type,
    name = name,
    data = data,
    timestamp = timestamp,
    sent = sent
)

fun List<HitDbo>.asDomainModel() = map { it.asDomainModel() }