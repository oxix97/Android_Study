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
        private var INSTACE: MainDatabase? = null
        fun getDatabase(
            context: Context,
            viewModelScope: CoroutineScope,
        ): MainDatabase {
            return INSTACE ?: synchronized(this) {
                val instace = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTACE = instace
                instace
            }
        }
    }
}