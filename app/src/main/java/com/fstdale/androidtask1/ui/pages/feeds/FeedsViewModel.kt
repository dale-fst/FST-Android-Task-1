package com.fstdale.androidtask1.ui.pages.feeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fstdale.androidtask1.data.models.Tweet
import com.fstdale.androidtask1.data.repositories.TweetRepository
import com.fstdale.androidtask1.utils.Constant
import com.fstdale.androidtask1.utils.Utils
import kotlinx.coroutines.*

class FeedsViewModel(private val tweetRepository: TweetRepository) : ViewModel() {

    var tweetList: MutableLiveData<ArrayList<Tweet>> = MutableLiveData()

    fun getTweetList(forceReplace: Boolean = false) {
        viewModelScope.launch {
            val local = tweetRepository.getTweets()
            if(!forceReplace && local.isNotEmpty()) {
                tweetList.postValue(ArrayList(local))
            } else {
                val response = tweetRepository.getTweetsApi()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val list = response.body()
                        list?.forEach {
                            it.name = Constant.TWITTER_ARTIST
                            it.timestamp = Utils.convertDate(it.created_at)
                        }
                        tweetList.postValue(list)
                        tweetRepository.insertTweets(list!!)
                    }
                }
            }
        }
    }
}