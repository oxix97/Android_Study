package com.example.mvvm.test

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contact")
data class TestData(
    //null인 경우 자동으로 생성해준다.
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    //같으면 생략이 가능하다.
    @ColumnInfo(name = "number")
    var number: String,

    @ColumnInfo(name = "initial")
    var initial: String
) {
    //초기화
    constructor() : this(null, "", "", "")
}
