package com.example.myapplication.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MainDao {

    @Query("SELECT * FROM test_data ORDER BY id DESC")
    fun getAll(): LiveData<List<MainData>>

    @Query("DELETE FROM test_data")
    fun deleteAll()

    @Delete
    fun delete(data: MainData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: MainData)

}