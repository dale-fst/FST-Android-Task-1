package com.fstdale.androidtask1.data.repositories

import com.fstdale.androidtask1.data.firebase.FirebaseSource
import com.fstdale.androidtask1.data.models.User
import com.google.firebase.auth.AuthResult

class UserRepository (private val firebase: FirebaseSource){

    suspend fun login(email: String, password: String) : AuthResult = firebase.login(email, password)

    suspend fun register(email: String, password: String) : AuthResult = firebase.register(email, password)

    suspend fun createUser(user: User) = firebase.createUser(user)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()

    fun getUserDetails() = firebase.getUserDetails()
}