package com.fstdale.androidtask1.ui.pages.feeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fstdale.androidtask1.data.models.Tweet
import com.fstdale.androidtask1.data.repositories.TweetRepository
import com.fstdale.androidtask1.utils.Constant
import com.fstdale.androidtask1.utils.Utils
import kotlinx.coroutines.*

class FeedsViewModel(private val tweetRepository: TweetRepository) : ViewModel() {

    var tweetList: MutableLiveData<ArrayList<Tweet>> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    fun getTweetList(forceReplace: Boolean = false) {
        job = CoroutineScope(Dispatchers.IO).launch {
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
                        loading.value = false
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}