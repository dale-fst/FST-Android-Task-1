package com.fstdale.androidtask1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweet")
class Tweet(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val timestamp: String
) {
}