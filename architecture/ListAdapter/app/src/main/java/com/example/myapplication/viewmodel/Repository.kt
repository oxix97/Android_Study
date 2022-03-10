package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.myapplication.model.MainData
import com.example.myapplication.model.MainDatabase

class Repository(mDatabase: MainDatabase) {
    private val dao = mDatabase.dao()
    val dataList: LiveData<List<MainData>> = dao.getAll()

    companion object {
        private var sINSTANCE: Repository? = null
        fun getInstance(database: MainDatabase): Repository {
            return sINSTANCE ?: synchronized(this) {
                val instance = Repository(database)
                sINSTANCE = instance
                instance
            }
        }
    }

    fun insert(data: MainData) {
        dao.insert(data)
    }

    fun delete(data: MainData) {
        dao.delete(data)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

}