package com.example.network.operation

import retrofit2.http.GET
//import retrofit2.http.Path

interface UserAPI {
    @GET("user")
    suspend fun getUserById(): User
}