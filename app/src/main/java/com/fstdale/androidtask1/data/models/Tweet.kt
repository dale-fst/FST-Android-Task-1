package com.fstdale.androidtask1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweet")
data class Tweet(
    @PrimaryKey
    val id: Long,
    val name: String,
    val text: String,
    val created_at: String
)