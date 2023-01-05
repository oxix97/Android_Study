package com.example.reviews.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reviews.dao.HistoryDao
import com.example.reviews.dao.ReviewDao
import com.example.reviews.data.History
import com.example.reviews.data.Review

@Database(entities = [History::class, Review::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun reviewDao(): ReviewDao
}