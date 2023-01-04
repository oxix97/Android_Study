package com.example.reviews.data

import com.google.gson.annotations.SerializedName

data class SearchBookDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("item")
    val books: List<BookInfoData>
)
