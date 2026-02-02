package com.example.minimalmoves.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.minimalmoves.ui.components.StatItem
import com.example.minimalmoves.viewmodel.GameViewModel

@Composable
fun MenuScreen(
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
) {
    val total = viewModel.totalGames.collectAsState().value
    val wins = viewModel.winsCount.collectAsState().value
    val rate = viewModel.winRate.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        /* ===== TITLE ===== */

        Text(
            text = "Minimal Moves",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Dosáhni cílového čísla na co nejméně tahů",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(32.dp))

        /* ===== START BUTTON ===== */

        Button(
            onClick = viewModel::startRandomGame,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🎮 Start hry")
        }

        Spacer(Modifier.height(24.dp))

        /* ===== STATISTICS CARD ===== */

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "📊 Statistiky",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("🎮", "Hry", total.toString())
                    StatItem("🏆", "Výhry", wins.toString())
                    StatItem("📈", "Úspěšnost", "$rate %")
                }
            }
        }
    }
}
