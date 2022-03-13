package com.example.myapplication.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [MainData::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun dao(): MainDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null
        fun getDatabase(
            context: Context,
            viewModelScope: CoroutineScope,
        ): MainDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}