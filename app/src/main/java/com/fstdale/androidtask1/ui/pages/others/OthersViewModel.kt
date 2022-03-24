package com.fstdale.androidtask1.ui.pages.others

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fstdale.androidtask1.data.models.User
import com.fstdale.androidtask1.data.repositories.UserRepository
import com.fstdale.androidtask1.ui.pages.auth.LoginActivity

class OthersViewModel(private val repository: UserRepository) : ViewModel() {

    val progress = MutableLiveData(true)
    val name = MutableLiveData("")

    fun getUserDetails() {
        if(repository.currentUser() == null)
            return

        repository.getUserDetails().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                val user: User = document.toObject(User::class.java)!!
                name.postValue("${user.firstname} ${user.lastname}")
            }
            progress.postValue(false)
        }
    }

    fun logout(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
        repository.logout()
    }
}