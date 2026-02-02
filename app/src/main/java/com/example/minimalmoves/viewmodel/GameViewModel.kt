package com.example.minimalmoves.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimalmoves.data.Level
import com.example.minimalmoves.data.db.AppDatabase
import com.example.minimalmoves.data.db.GameResultEntity
import com.example.minimalmoves.repository.GameResultRepository
import com.example.minimalmoves.repository.LevelRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class GameViewModel(application: Application) : AndroidViewModel(application) {

    /* ================= DATABASE ================= */

    private val db = AppDatabase.getDatabase(application)
    private val resultRepository = GameResultRepository(db.gameResultDao())

    /* ================= LEVELS ================= */

    private val levelRepository = LevelRepository(application)
    private val levels: List<Level> = levelRepository.loadLevels()

    private val _operations = MutableStateFlow<List<String>>(emptyList())
    val operations: StateFlow<List<String>> = _operations

    /* ================= GAME STATE ================= */

    private val _gameStarted = MutableStateFlow(false)
    val gameStarted: StateFlow<Boolean> = _gameStarted

    private val _gameFinished = MutableStateFlow(false)
    val gameFinished: StateFlow<Boolean> = _gameFinished

    private val _currentValue = MutableStateFlow(0)
    val currentValue: StateFlow<Int> = _currentValue

    private val _targetValue = MutableStateFlow(0)
    val targetValue: StateFlow<Int> = _targetValue

    private val _moves = MutableStateFlow(0)
    val moves: StateFlow<Int> = _moves

    /* ================= STATISTICS ================= */

    val gameResults = resultRepository.getResults()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val bestResult = resultRepository.getBestResult()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    val totalGames = resultRepository.getTotalGames()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val winsCount = resultRepository.getWinsCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val averageMovesForWins = resultRepository.getAverageMovesForWins()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    val winRate = combine(totalGames, winsCount) { total, wins ->
        if (total == 0) 0 else (wins * 100) / total
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    /* ================= GAME CONTROL ================= */

    fun startRandomGame() {
        if (levels.isEmpty()) return

        val level = levels.random()

        _currentValue.value = level.start
        _targetValue.value = level.target
        _operations.value = level.operations
        _moves.value = 0
        _gameFinished.value = false
        _gameStarted.value = true
    }

    fun backToMenu() {
        _gameStarted.value = false
    }

    /* ================= GAME LOGIC ================= */

    fun applyOperation(operation: String) {
        if (_gameFinished.value) return

        val number = operation.drop(1).toIntOrNull() ?: return

        _currentValue.value = when {
            operation.startsWith("+") -> _currentValue.value + number
            operation.startsWith("-") -> _currentValue.value - number
            operation.startsWith("*") -> _currentValue.value * number
            operation.startsWith("/") && number != 0 -> _currentValue.value / number
            else -> _currentValue.value
        }

        _moves.value++
        checkWinCondition()
    }

    /* ================= DATABASE ================= */

    fun clearHistory() {
        viewModelScope.launch {
            resultRepository.clearResults()
        }
    }

    private fun saveGameResult() {
        val result = GameResultEntity(
            startValue = currentValue.value,
            targetValue = targetValue.value,
            moves = moves.value,
            success = true,
            timestamp = System.currentTimeMillis()
        )

        viewModelScope.launch {
            resultRepository.saveResult(result)
        }
    }


    private fun checkWinCondition() {
        if (_currentValue.value == _targetValue.value && !_gameFinished.value) {
            _gameFinished.value = true
            saveGameResult()
        }
    }
}
