package com.example.reviews.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reviews.dao.HistoryDao
import com.example.reviews.data.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}