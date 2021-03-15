package com.example.myhillsblog.retrofit

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun getBlogs():List<BlogNetworkEntity>
}