package com.example.reviews.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "review") val review: String?
)
