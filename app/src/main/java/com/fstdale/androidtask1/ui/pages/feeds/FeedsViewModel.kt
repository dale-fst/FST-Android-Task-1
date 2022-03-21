package com.fstdale.androidtask1.ui.pages.feeds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fstdale.androidtask1.data.models.Tweet

class FeedViewModel(val app: Application) : AndroidViewModel(app) {

    var tweetList: ArrayList<Tweet> = ArrayList()
    var data: MutableLiveData<ArrayList<Tweet>> = MutableLiveData()

    fun getTweetList() {
        tweetList.add(Tweet(1, "@dale", "Hello 1", "1647896284000"))
        tweetList.add(Tweet(2, "@dale", "Hello 2", "1647896303000"))
        data.postValue(tweetList)
    }

}