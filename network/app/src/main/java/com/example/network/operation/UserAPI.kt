package com.example.network.operation

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

//import retrofit2.http.Path

interface UserAPI {
    @GET("user/{userId}")
    suspend fun getUserById(@Path("userId") id: Int): User

    @GET("user/list")
    suspend fun getUserList(@QueryMap map: Map<String, String>): List<User>
}