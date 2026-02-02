package com.example.minimalmoves.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.minimalmoves.ui.components.ConfettiEffect
import com.example.minimalmoves.ui.components.GameResultItem
import com.example.minimalmoves.ui.components.RatingStars
import com.example.minimalmoves.viewmodel.GameViewModel
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GamePlayScreen(
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
) {
    val value = viewModel.currentValue.collectAsState().value
    val target = viewModel.targetValue.collectAsState().value
    val moves = viewModel.moves.collectAsState().value
    val finished = viewModel.gameFinished.collectAsState().value
    val operations = viewModel.operations.collectAsState().value
    val results = viewModel.gameResults.collectAsState().value

    val valueScale = animateFloatAsState(
        targetValue = if (finished) 1.15f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
        label = "valueScale"
    ).value

    Box(modifier = modifier.fillMaxSize()) {

        /* ===== KONFETY ===== */
        ConfettiEffect(isActive = finished)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /* ===== VÝHRA ===== */
            AnimatedVisibility(
                visible = finished,
                enter = scaleIn() + fadeIn()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "🎉 Vyhráno!",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    RatingStars(moves)
                    Spacer(Modifier.height(16.dp))
                }
            }

            /* ===== HLAVNÍ KARTA ===== */
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = value.toString(),
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .scale(valueScale),
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Cíl: $target",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Tahy: $moves",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            /* ===== OPERACE ===== */
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                operations.forEach { op ->
                    Button(
                        onClick = { viewModel.applyOperation(op) },
                        enabled = !finished,
                        modifier = Modifier.defaultMinSize(minWidth = 80.dp)
                    ) {
                        Text(op, maxLines = 1, overflow = TextOverflow.Clip)
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            /* ===== OVLÁDÁNÍ ===== */
            Button(
                onClick = viewModel::startRandomGame,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Nový náhodný level")
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = viewModel::backToMenu,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Zpět do menu")
            }

            Spacer(Modifier.height(16.dp))

            if (results.isNotEmpty()) {
                Spacer(Modifier.height(24.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Historie her",
                        style = MaterialTheme.typography.titleMedium
                    )
                }



                Spacer(Modifier.height(8.dp))
            }

            var showDialog by remember { mutableStateOf(false) }

            OutlinedButton(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🗑️ Smazat historii")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.clearHistory()
                            showDialog = false
                        }) {
                            Text("Smazat")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Zrušit")
                        }
                    },
                    title = { Text("Smazat historii") },
                    text = { Text("Opravdu chceš smazat celou historii her?") }
                )
            }


            /* ===== HISTORIE ===== */
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
            ) {
                items(results) {
                    GameResultItem(it)
                }
            }
        }
    }
}
