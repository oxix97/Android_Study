package com.example.reviews.api.datasource

import com.example.reviews.BuildConfig
import com.example.reviews.api.BookService
import com.example.reviews.data.BestSellerDto
import com.example.reviews.data.SearchBookDto
import retrofit2.Call

class BookDataSourceImpl(
    private val service: BookService
) : BookDataSource {
    override fun getBestSellers(): Call<BestSellerDto> {
        return service.getBestSellerBooks(BuildConfig.API_KEY)
    }

    override fun getSearchBooks(query: String): Call<SearchBookDto> {
        return service.getBookName(BuildConfig.API_KEY, query)
    }
}