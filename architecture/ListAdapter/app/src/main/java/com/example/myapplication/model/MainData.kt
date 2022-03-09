package com.example.myapplication.model

import androidx.room.Entity
import java.util.*

@Entity(tableName = "test_data")
data class MainData(
    val id: Int?,
    val title: String,
    val content: String,
    val date: Date,
)
