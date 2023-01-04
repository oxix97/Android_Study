package com.example.reviews.api

import com.example.reviews.data.BestSellerDto
import com.example.reviews.data.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("/api/bestSeller.api?ouput=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ): Call<BestSellerDto>

    @GET("/api/search.api?output=json")
    fun getBookName(
        @Query("key") apikey: String,
        @Query("query") query: String,
    ): Call<SearchBookDto>
}