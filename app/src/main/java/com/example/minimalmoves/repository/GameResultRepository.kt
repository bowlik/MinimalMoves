package com.example.minimalmoves.repository

import com.example.minimalmoves.data.db.GameResultDao
import com.example.minimalmoves.data.db.GameResultEntity
import kotlinx.coroutines.flow.Flow

class GameResultRepository(private val dao: GameResultDao) {

    suspend fun saveResult(result: GameResultEntity) {
        dao.insertResult(result)
    }

    fun getResults(): Flow<List<GameResultEntity>> {
        return dao.getAllResults()
    }
    fun getBestResult(): Flow<GameResultEntity?> {
        return dao.getBestResult()
    }
    suspend fun clearResults() {
        dao.deleteAllResults()
    }

    fun getTotalGames(): Flow<Int> = dao.getTotalGames()

    fun getWinsCount(): Flow<Int> = dao.getWinsCount()

    fun getAverageMovesForWins(): Flow<Double?> =
        dao.getAverageMovesForWins()


}
