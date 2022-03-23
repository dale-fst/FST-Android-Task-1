package com.fstdale.androidtask1.data.repositories

import android.content.Context
import androidx.room.Room
import com.fstdale.androidtask1.data.db.TweetDatabase
import com.fstdale.androidtask1.data.models.Tweet
import com.fstdale.androidtask1.data.services.RetrofitService
import com.fstdale.androidtask1.utils.Constant
import retrofit2.Response

class TweetRepository constructor(
    context: Context,
    private val retrofitService: RetrofitService) {

    private inline fun <reified T : Any> objectOf() = T::class.java

    private var tweetDatabase: TweetDatabase =
        Room.databaseBuilder(context, objectOf<TweetDatabase>(), "local.db")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()

    suspend fun getTweetsApi() = retrofitService
        .getTweets(Constant.TWITTER_ARTIST, Constant.TWITTER_PAGE)

    fun getTweets() = tweetDatabase.tweetDao().getTweets()

    fun insertTweets(list: ArrayList<Tweet>) = tweetDatabase
        .tweetDao().insertTweets(list)
}