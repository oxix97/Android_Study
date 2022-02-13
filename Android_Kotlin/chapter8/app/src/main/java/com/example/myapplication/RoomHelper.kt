package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

//이게 무슨의미 인지 한번 더 검토
@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract fun roomMemoDao(): RoomMemoDao
}