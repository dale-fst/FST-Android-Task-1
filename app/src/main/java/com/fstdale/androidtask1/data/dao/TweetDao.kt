package com.fstdale.androidtask1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fstdale.androidtask1.data.models.Tweet

@Dao
interface TweetDao {
    @Query("SELECT * FROM tweet order by timestamp DESC")
    fun getTweets(feedIds: ArrayList<Int>): List<Tweet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweet(tweet: Tweet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweets(tweets: List<Tweet>)
}