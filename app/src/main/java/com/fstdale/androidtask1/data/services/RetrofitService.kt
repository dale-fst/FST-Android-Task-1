package com.fstdale.androidtask1.data.services

import com.fstdale.androidtask1.data.models.Tweet
import com.fstdale.androidtask1.utils.OAuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

interface RetrofitService {

    @GET("user_timeline.json")
    suspend fun getTweets(
        @Query("screen_name") name: String,
        @Query("count") count: Int)
    : Response<ArrayList<Tweet>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            val url = "https://api.twitter.com/1.1/statuses/"
            val accessToken = "AAAAAAAAAAAAAAAAAAAAAKb5aQEAAAAAmuuFvM5q8NzMQIXySFpw1Ow2Pbg%3D9zn6oRqFVoWwDI5NRr3egqxmpCD14xL6BkZIT8Z4bM9uqhmAqP"
            val client = OkHttpClient.Builder()
                .addInterceptor(OAuthInterceptor("Bearer", accessToken))
                .build()
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}