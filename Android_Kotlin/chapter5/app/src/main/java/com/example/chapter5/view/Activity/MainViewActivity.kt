package com.example.chapter5.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.ListFragment
import com.example.chapter5.R
import com.example.chapter5.databinding.ActivityMainViewBinding
import com.example.chapter5.view.Fragment.OneFragment
import com.example.chapter5.view.Fragment.TwoFragment

class MainViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment()
    }

    private fun setFragment() {
        val oneFragment: OneFragment = OneFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fr_container, oneFragment)
        transaction.commit()
    }
    private fun nextFragment() {
        val twoFragment : TwoFragment = TwoFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fr_container,twoFragment)
        transaction.addToBackStack("two")
        transaction.commit()
    }
}