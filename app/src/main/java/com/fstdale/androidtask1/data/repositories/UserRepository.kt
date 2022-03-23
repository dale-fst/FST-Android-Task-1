package com.fstdale.androidtask1.data.repositories

import com.fstdale.androidtask1.data.firebase.FirebaseSource
import com.fstdale.androidtask1.data.models.User

class UserRepository (private val firebase: FirebaseSource){

    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(user: User, password: String) = firebase.register(user, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()

    fun getUserDetails() = firebase.getUserDetails()
}