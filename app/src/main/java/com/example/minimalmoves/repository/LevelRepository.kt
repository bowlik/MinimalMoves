package com.example.minimalmoves.repository

import android.content.Context
import com.example.minimalmoves.data.Level
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LevelRepository(private val context: Context) {

    fun loadLevels(): List<Level> {
        val json = context.assets
            .open("levels.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<Level>>() {}.type
        return Gson().fromJson(json, type)
    }
}
