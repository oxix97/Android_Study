package com.example.lotto_5000.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lotto_5000.util.NumberDao

@Database(entities = [NumberData::class], version = 1)
abstract class NumberDatabase : RoomDatabase() {
    abstract fun numberDao(): NumberDao
}