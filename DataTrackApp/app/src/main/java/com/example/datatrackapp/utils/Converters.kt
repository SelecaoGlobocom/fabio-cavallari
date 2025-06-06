package com.example.datatrackapp.utils

import androidx.room.TypeConverter
import com.example.datatrackapp.domain.model.HitType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromMap(map: Map<String, String>): String = gson.toJson(map)

    @TypeConverter
    fun toMap(json: String): Map<String, String> {
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromHitType(type: HitType): String = type.name

    @TypeConverter
    fun toHitType(name: String): HitType = HitType.valueOf(name)
}