package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.MainData
import com.example.myapplication.model.MainDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val repository: Repository = Repository(MainDatabase.getDatabase(application, viewModelScope))
    var dataList: LiveData<List<MainData>> = repository.dataList

    fun insert(data: MainData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(data)
    }

    fun delete(data: MainData) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(data)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun getAll(): LiveData<List<MainData>> = dataList
}