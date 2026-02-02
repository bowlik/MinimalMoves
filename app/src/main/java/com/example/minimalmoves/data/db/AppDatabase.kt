package com.example.minimalmoves.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [GameResultEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameResultDao(): GameResultDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "minimal_moves_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}