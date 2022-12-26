package com.example.remoteconfigtest

import android.graphics.pdf.PdfDocument.Page
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.remoteconfigtest.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.awaitAll
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: PageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initAdapter()
    }

    private fun initAdapter() {

    }

    private fun initData() {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(remoteConfigSettings {
            //서버에서 블락하지 않는 이상 곧 바로 패치됨
            minimumFetchIntervalInSeconds = 0
        })
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                val data = parseData(remoteConfig.getString("name"))
                val isNameRevealed = remoteConfig.getBoolean("is_name_revealed")

                displayDataPager(data, isNameRevealed)
            }
        }
    }

    private fun displayDataPager(data: List<RemoteData>, nameRevealed: Boolean) {
        listAdapter = PageAdapter(nameRevealed)
        listAdapter.apply {
            submitList(data)
        }

        binding.vpContainer.apply {
            this.adapter = listAdapter
            setCurrentItem(listAdapter.itemCount / 2, false)
        }
    }

    private fun parseData(json: String): List<RemoteData> {
        val jsonArr = JSONArray(json)
        var jsonList = emptyList<JSONObject>()
        for (idx in 0 until jsonArr.length()) {
            val jsonObj = jsonArr.getJSONObject(idx)
            jsonObj?.let { jsonList = jsonList + it }
        }
        return jsonList.map {
            RemoteData(
                name = it.getString("name"), price = it.getInt("price")
            )
        }
    }
}