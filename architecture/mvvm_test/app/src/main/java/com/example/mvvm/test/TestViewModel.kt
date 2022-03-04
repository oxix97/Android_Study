package com.example.mvvm.test

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TestViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TestRepository(application)
    private val dataList = repository.getAll()

    fun getAll(): LiveData<List<TestData>> {
        return this.dataList
    }

    fun insert(data: TestData) {
        repository.insert(data)
    }

    fun delete(data: TestData) {
        repository.delete(data)
    }
}