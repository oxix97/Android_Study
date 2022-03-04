package com.example.lotto_5000.util

import androidx.room.Insert
import androidx.room.Dao
import androidx.room.Query
import com.example.lotto_5000.data.NumberData

@Dao
interface NumberDao {

    @Query("SELECT * FROM numberData")
    fun getAll(): List<NumberData>

    @Insert
    fun insertNumber(numberData: NumberData)

    @Query("DELETE FROM numberData")
    fun deleteAll()
}