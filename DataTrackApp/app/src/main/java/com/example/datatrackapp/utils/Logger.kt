package com.example.datatrackapp.utils

import android.util.Log
import com.google.gson.GsonBuilder

object Logger {
    const val LOG_TAG = "DataTrackTag"

    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun log(model: Any?) {
        val content = model as? String? ?: gson.toJson(model)
        Log.d(LOG_TAG, content)
    }
}