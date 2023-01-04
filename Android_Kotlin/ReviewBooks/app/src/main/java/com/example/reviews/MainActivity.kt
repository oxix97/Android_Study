package com.example.reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reviews.adapter.BookListAdapter
import com.example.reviews.api.BookService
import com.example.reviews.data.BestSellerDto
import com.example.reviews.data.SearchBookDto
import com.example.reviews.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: BookListAdapter
    private lateinit var service: BookService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        initRetrofit()
        initAdapter()
        initView()
        keyboardListener()
    }

    private fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(BookService::class.java)
    }

    private fun keyboardListener() {
        binding.etSearchText.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == MotionEvent.ACTION_DOWN) {
                searchText(binding.etSearchText.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

    }

    private fun searchText(keyword: String) {
        service.getBookName(keyword).enqueue(object : Callback<SearchBookDto> {
            override fun onResponse(call: Call<SearchBookDto>, response: Response<SearchBookDto>) {
                if (!response.isSuccessful) {
                    return
                }
                response.body()?.let {
                    listAdapter.submitList(it.books)
                }
            }

            override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun initAdapter() {
        listAdapter = BookListAdapter()
        binding.rvContainer.layoutManager = LinearLayoutManager(this)
        binding.rvContainer.adapter = listAdapter
    }

    private fun initView() {
        service.getBestSellerBooks().enqueue(object : Callback<BestSellerDto> {
            override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                if (!response.isSuccessful) {
                    return
                }
                response.body()?.let {
                    listAdapter.submitList(it.books)
                    Timber.d("books : $it")
                }
            }

            override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                Timber.d("error : $t")
            }
        })
    }
}