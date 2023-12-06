package com.pavan.retrofitkotlin.Interfaces

import com.pavan.retrofitkotlin.models.UsersItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/users")
    fun getdata() : Call<List<UsersItem>>
}