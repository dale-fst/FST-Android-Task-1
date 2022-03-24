package com.fstdale.androidtask1.ui.pages.home

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.ui.pages.auth.SignupActivity

class HomeViewModel(val app: Application) : AndroidViewModel(app) {

    var url = MutableLiveData<String>()

    fun setURL() {
        url.postValue(app.getString(R.string.URL_PLAYGOOSE))
    }

    fun singUp() {
        app.startActivity(Intent(app, SignupActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}