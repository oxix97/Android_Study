package com.example.calculator.util

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.calculator.data.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun getAll(): List<History>
    //모든 history 조회
    @Insert
    fun insertHistory(history: History)

//    @Delete
//    fun delete(history: History)

    @Query("DELETE FROM history")
    fun deleteAll()

//    @Query("SELECT * FROM history WHERE result LIKE :result LIMIT 1")
//    fun findByResult(result: String)
}