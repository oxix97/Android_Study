package com.example.mvvm.test

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TestDataDao {

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<TestData>>

    //onConflict -> 중복된 데이터를 어떻게 처리할지 정한다.
    // OnConflictStrategy 인터페이스를 호출해 REPLACE, IGNORE, ABORT, FAIL, ROLLBACK 등의 액션이 지정 가능하다.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(TestData: TestData)

    @Delete
    fun delete(TestData: TestData)
}