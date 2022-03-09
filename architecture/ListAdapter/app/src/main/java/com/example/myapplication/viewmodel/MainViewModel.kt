package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.MainDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val repository: Repository = Repository(MainDatabase.getDatabase(application, viewModelScope))

}