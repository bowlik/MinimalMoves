package com.example.minimalmoves.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.minimalmoves.viewmodel.GameViewModel

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
) {
    val started by viewModel.gameStarted.collectAsState()

    if (started) {
        GamePlayScreen(viewModel, modifier)
    } else {
        MenuScreen(viewModel, modifier)
    }
}
