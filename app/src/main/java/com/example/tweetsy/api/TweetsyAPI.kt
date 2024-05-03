package com.example.tweetsy.api

import com.example.tweetsy.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyAPI {
    @GET("/v3/b/6632fbbbe41b4d34e4ed82e5?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path")category:String): Response<List<TweetListItem>>


    @GET("/v3/b/6632fbbbe41b4d34e4ed82e5?meta=false")
    @Headers("X-JSON-PATH : tweets..category")
    suspend fun getCategories():Response<List<String>>
}