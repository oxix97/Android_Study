package com.example.chapter5.view.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chapter5.R
import com.example.chapter5.databinding.FragmentOneBinding
import com.example.chapter5.view.Activity.MainViewActivity

class OneFragment : Fragment() {
    var mainActivity: MainViewActivity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater, container, false)
        binding.btnNext.setOnClickListener {
            mainActivity?.nextFragment()
        }
        binding.tvText.text = arguments?.getString("key1")
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainViewActivity) mainActivity = context
    }
}