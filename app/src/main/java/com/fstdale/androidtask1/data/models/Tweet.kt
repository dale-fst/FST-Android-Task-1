package com.fstdale.androidtask1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fstdale.androidtask1.utils.Constant

@Entity(tableName = "tweet")
data class Tweet(
    @PrimaryKey
    val id: Long,
    val text: String
) {
    var name: String = Constant.TWITTER_ARTIST
    var created_at: String = ""
    var timestamp: String = ""
}