package com.fstdale.androidtask1.ui.pages.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class HomeViewModel(val app: Application) : AndroidViewModel(app) {

    var url = MutableLiveData<String>()

    fun setURL() {
        url.postValue("https://staging-home-playgoose-standalone.dsign.gift/artist/playgoose")
    }
}