package com.fstdale.androidtask1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude

@Entity
data class User(
    val firstname: String? = null,
    val lastname: String? = null,
    val email: String? = null,
    @PrimaryKey
    @get:Exclude
    var uid: String = ""
)