package com.example.chapter5.view.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chapter5.R
import com.example.chapter5.databinding.FragmentTwoBinding
import com.example.chapter5.view.Activity.MainViewActivity

class TwoFragment : Fragment() {
    lateinit var mainActivity: MainViewActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTwoBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener {
            mainActivity?.goBack()
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainViewActivity
    }
}