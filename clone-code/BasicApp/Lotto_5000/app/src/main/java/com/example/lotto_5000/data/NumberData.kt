package com.example.lotto_5000.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberData(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "number1") val number1: String?,
    @ColumnInfo(name = "number2") val number2: String?,
    @ColumnInfo(name = "number3") val number3: String?,
    @ColumnInfo(name = "number4") val number4: String?,
    @ColumnInfo(name = "number5") val number5: String?,
    @ColumnInfo(name = "number6") val number6: String?,
)
