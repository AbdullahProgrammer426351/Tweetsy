package com.example.tweetsy.repository

import com.example.tweetsy.api.TweetsyAPI
import com.example.tweetsy.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsyAPI: TweetsyAPI) {
    // Repository is a class that brings data from data source (api, local storage or database etc) to us.
    // It contains functions that do this task.
    // Simply it is a class in which we define functions that tells from where we have to bring data.

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories


    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories() {
        val response = tweetsyAPI.getCategories()
        if (response.isSuccessful && response.body() != null) {
            // here data will be provided to us which we have to pass to viewmodel and then to views
            // to do this, we will use observable
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category:String) {
        val response = tweetsyAPI.getTweets(category)
        if (response.isSuccessful && response.body() != null) {
            // here data will be provided to us which we have to pass to viewmodel and then to views
            // to do this, we will use observable
            _tweets.emit(response.body()!!)
        }
    }
}