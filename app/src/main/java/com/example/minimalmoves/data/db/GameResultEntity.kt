package com.example.minimalmoves.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_results")
data class GameResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val startValue: Int,
    val targetValue: Int,
    val moves: Int,
    val success: Boolean,
    val timestamp: Long
)