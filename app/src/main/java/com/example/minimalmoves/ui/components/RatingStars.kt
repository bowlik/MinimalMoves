package com.example.minimalmoves.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun RatingStars(moves: Int) {
    val stars = when {
        moves <= 10 -> 3
        moves <= 20 -> 2
        else -> 1
    }

    Row {
        repeat(stars) {
            Text("⭐")
        }
    }
}
