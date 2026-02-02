package com.example.minimalmoves

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minimalmoves.ui.screen.GameScreen
import com.example.minimalmoves.ui.theme.MinimalMovesTheme
import com.example.minimalmoves.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MinimalMovesTheme {
                Scaffold { innerPadding ->
                    val vm: GameViewModel = viewModel(
                        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                    )

                    GameScreen(
                        viewModel = vm,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
