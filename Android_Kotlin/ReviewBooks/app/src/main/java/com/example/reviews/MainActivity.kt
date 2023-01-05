package com.example.reviews

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.reviews.adapter.BookListAdapter
import com.example.reviews.adapter.HistoryAdapter
import com.example.reviews.api.BookService
import com.example.reviews.data.BestSellerDto
import com.example.reviews.data.History
import com.example.reviews.data.SearchBookDto
import com.example.reviews.database.AppDatabase
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
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var service: BookService
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDatabase()
        Timber.plant(Timber.DebugTree())
        initRetrofit()
        initAdapter()
        initView()
    }

    private fun initDatabase() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "BookSearchDB",
        ).build()
    }

    private fun initRetrofit() {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(BookService::class.java)
    }

    private fun saveSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().insertHistory(History(null, keyword))
        }.start()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun keyboardListener() {
        binding.etSearchText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                showHistoryView()
            }
            return@setOnTouchListener false
        }


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
                saveSearchKeyword(keyword)
                hideHistoryView()
            }

            override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {
                hideHistoryView()
            }
        })
    }

    private fun initAdapter() {
        listAdapter = BookListAdapter()
        binding.rvContainer.layoutManager = LinearLayoutManager(this)
        binding.rvContainer.adapter = listAdapter

        historyAdapter = HistoryAdapter { keyword ->
            deleteSearchKeyword(keyword)
        }
        binding.rvHistoryContainer.layoutManager = LinearLayoutManager(this)
        binding.rvHistoryContainer.adapter = historyAdapter
        keyboardListener()
    }

    private fun deleteSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().delete(keyword)
            showHistoryView()
        }.start()
    }

    private fun showHistoryView() {
        Thread {
            val keywords = db.historyDao().getAll().reversed()
            runOnUiThread {
                binding.rvHistoryContainer.isVisible = true
                historyAdapter.submitList(keywords.orEmpty())
            }
        }.start()
    }

    private fun hideHistoryView() {
        binding.rvHistoryContainer.isVisible = false
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