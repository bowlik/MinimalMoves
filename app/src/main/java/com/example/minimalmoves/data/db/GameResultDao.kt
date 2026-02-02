package com.example.minimalmoves.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameResultDao {

    @Insert
    suspend fun insertResult(result: GameResultEntity)

    //smaž všechny řádky ve výsledku
    @Query("DELETE FROM game_results")
    suspend fun deleteAllResults()


    @Query("SELECT * FROM game_results ORDER BY timestamp DESC")
    fun getAllResults(): Flow<List<GameResultEntity>>

    @Query("""
    SELECT * FROM game_results
    WHERE success = 1
    ORDER BY moves ASC
    LIMIT 1
""")
    fun getBestResult(): Flow<GameResultEntity?>

    @Query("SELECT COUNT(*) FROM game_results")
    fun getTotalGames(): Flow<Int>

    @Query("SELECT COUNT(*) FROM game_results WHERE success = 1")
    fun getWinsCount(): Flow<Int>

    @Query("SELECT AVG(moves) FROM game_results WHERE success = 1")
    fun getAverageMovesForWins(): Flow<Double?>

    @Query("DELETE FROM game_results")
    suspend fun clearAll()



}