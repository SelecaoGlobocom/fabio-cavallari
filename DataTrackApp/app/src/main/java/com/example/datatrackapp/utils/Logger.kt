package com.example.datatrackapp.utils

import android.util.Log
import com.google.gson.GsonBuilder

object Logger {
    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun log(model: Any) {
        Log.d(">>>", gson.toJson(model))
    }
}