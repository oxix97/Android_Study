package com.example.reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.reviews.data.BookInfoData
import com.example.reviews.data.Review
import com.example.reviews.database.AppDatabase
import com.example.reviews.databinding.ActivityDetailBinding
import timber.log.Timber

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatabase()
        initView()
    }

    private fun saveReview(data: BookInfoData?) {
        binding.btnDetailSave.setOnClickListener {
            Thread {
                db.reviewDao().saveReview(
                    Review(
                        data?.id?.toInt() ?: 0,
                        binding.etDetailReview.text.toString()
                    )
                )
                Timber.e("id save : ${data?.id?.toInt() ?: 0}")
            }.start()
        }
    }

    private fun initView() {
        val data = intent?.getParcelableExtra<BookInfoData>("bookInfo")
        with(binding) {
            tvDetailTitle.text = data?.title.orEmpty()
            tvDetailDescription.text = data?.description.orEmpty()
            Glide.with(ivDetailImage.context).load(data?.image).into(ivDetailImage)
        }
        Thread {
            val review = db.reviewDao().getOneReview(data?.id?.toInt() ?: 0)
            Timber.e("id data : ${data?.id?.toInt()}")
            Timber.e("id review : $review")
            if (review != null) {
                runOnUiThread {
                    binding.etDetailReview.setText(review.review.orEmpty())
                }
            }

        }.start()
        saveReview(data)
    }

    private fun initDatabase() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "BookSearchDB",
        ).build()
    }
}