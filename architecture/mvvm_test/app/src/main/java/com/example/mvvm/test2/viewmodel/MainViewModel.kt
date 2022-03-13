package com.example.mvvm.test2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm.test2.model.AppDatabase
import com.example.mvvm.test2.model.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val repository: Repository = Repository(AppDatabase.getDatabase(application, viewModelScope))
    val allUsers: LiveData<List<Entity>> = repository.allUsers

    suspend fun insert(entity: Entity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entity)
    }

     fun delete(entity: Entity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(entity)
    }

    suspend fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun getAll(): LiveData<List<Entity>> = allUsers
}