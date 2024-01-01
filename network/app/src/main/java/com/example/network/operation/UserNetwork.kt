package com.example.network.operation

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object UserNetwork {
    private val moshi = Moshi.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000/")// replace 127.0.0.1 into 10.0.2.2
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val userAPI: UserAPI = retrofit.create(UserAPI::class.java)
}


