package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.myapplication.model.MainData
import com.example.myapplication.model.MainDatabase

class Repository(mDatabase: MainDatabase) {
    private val dao = mDatabase.dao()
    val dataList: LiveData<List<MainData>> = dao.getAll()

    fun insert(data: MainData) {
        dao.insert(data)
    }

    fun delete(data: MainData) {
        dao.delete(data)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    companion object {
        private var INSTANCE: Repository? = null
        fun getInstace(database: MainDatabase): Repository {
            return INSTANCE ?: synchronized(this) {
                val instance = Repository(database)
                INSTANCE = instance
                instance
            }
        }
    }
}