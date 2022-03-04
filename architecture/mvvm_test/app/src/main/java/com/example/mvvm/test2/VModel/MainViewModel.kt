package com.example.mvvm.test2.VModel

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
    var allUsers: LiveData<List<Entity>> = repository.allUsers

    fun insert(entity: Entity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entity)
    }

    fun delete(entity: Entity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(entity)
    }

    fun getAll(): LiveData<List<Entity>> = allUsers
}