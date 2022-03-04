package com.example.calculator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator.util.HistoryDao

//History 사용한다는 등록
@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}