package com.pavan.retrofitkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavan.retrofitkotlin.Interfaces.ApiInterface
import com.pavan.retrofitkotlin.models.UsersItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var rvview: RecyclerView
    lateinit var myadapter: Adapter
    var BASE_URL = "https://api.github.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvview = findViewById(R.id.recylerview)
        rvview.layoutManager = LinearLayoutManager(this)
        myadapter = Adapter(this, emptyList()) // You can pass an empty list for now
        rvview.adapter = myadapter

        lifecycleScope.launch {
            getalldata()
        }
    }

    private fun getalldata() {
        val Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val respons = Retrofit.getdata();

        respons.enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                val data = response.body()
                if (data != null) {
                    myadapter.updatedata(data)
                }
                Log.d("data", response.toString())
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                Log.e("Mainactivity", "Error: ${t.message}")
            }

        })
    }
}