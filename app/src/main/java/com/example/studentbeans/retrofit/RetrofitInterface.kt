package com.example.studentbeans.retrofit

import com.example.studentbeans.model.PostsModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @get:GET("photos") // "/photos"
    val photos: Call<List<PostsModel?>?>?

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}