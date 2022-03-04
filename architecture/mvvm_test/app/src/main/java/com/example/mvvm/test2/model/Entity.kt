package com.example.mvvm.test2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var num: String,
)
