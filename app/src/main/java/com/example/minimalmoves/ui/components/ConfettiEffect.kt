package com.example.minimalmoves.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfettiEffect(isActive: Boolean) {
    if (!isActive) return
    Text("🎉🎉🎉")
}

