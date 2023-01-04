package com.example.reviews.data

import com.google.gson.annotations.SerializedName

data class BookInfoData(
    @SerializedName("itemId")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("coverSmallUrl")
    val image: String,
)
