package com.fstdale.androidtask1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fstdale.androidtask1.data.dao.TweetDao
import com.fstdale.androidtask1.data.models.Tweet

@Database(entities = [Tweet::class], version = 1)
abstract class TweetDatabase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao
}