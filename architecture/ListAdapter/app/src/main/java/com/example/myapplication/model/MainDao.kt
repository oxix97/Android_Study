package com.example.myapplication.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MainDao {

    @Query("select * from test_data order by id ASC")
    fun getAll(): LiveData<List<MainData>>

    @Query("delete from test_data")
    fun deleteAll()

    @Delete
    fun delete(data: MainData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: MainData)

}