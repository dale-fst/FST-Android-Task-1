package com.fstdale.androidtask1.ui.pages.others

import androidx.lifecycle.ViewModel
import com.fstdale.androidtask1.data.models.User
import com.fstdale.androidtask1.data.repositories.UserRepository

class OthersViewModel(private val repository: UserRepository) : ViewModel() {

    var updateListener: UpdateListener? = null

    fun getUserDetails() {
        if(repository.currentUser() == null)
            return

        repository.getUserDetails().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                val user: User = document.toObject(User::class.java)!!
                updateListener?.onUpdate("${user.firstname} ${user.lastname}")
            }
        }
    }

    fun logout() {
        repository.logout()
        updateListener?.onUpdate("")
    }
}