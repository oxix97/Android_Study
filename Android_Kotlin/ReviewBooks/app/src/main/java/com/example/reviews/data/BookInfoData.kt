package com.example.reviews.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookInfoData(
    @SerializedName("itemId")
    val id: Long?,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("coverSmallUrl")
    val image: String,
) : Parcelable
