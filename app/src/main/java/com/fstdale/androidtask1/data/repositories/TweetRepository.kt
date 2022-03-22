package com.fstdale.androidtask1.data.repositories

import com.fstdale.androidtask1.data.services.RetrofitService
import com.fstdale.androidtask1.utils.Constant

class TweetRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getTweets() = retrofitService
        .getTweets(Constant.TWITTER_ARTIST, Constant.TWITTER_PAGE)
}