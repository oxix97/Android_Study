package com.example.reviews.api.datasource

import com.example.reviews.data.BestSellerDto
import com.example.reviews.data.SearchBookDto
import retrofit2.Call

interface BookDataSource {
    fun getBestSellers(): Call<BestSellerDto>

    fun getSearchBooks(
        query: String
    ): Call<SearchBookDto>
}