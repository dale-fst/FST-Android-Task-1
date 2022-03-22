package com.fstdale.androidtask1.ui.pages.feeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fstdale.androidtask1.data.models.Tweet
import com.fstdale.androidtask1.data.repositories.TweetRepository
import kotlinx.coroutines.*

class FeedsViewModel(private val tweetRepository: TweetRepository) : ViewModel() {

    var tweetList: MutableLiveData<ArrayList<Tweet>> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    fun getTweetList() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = tweetRepository.getTweets()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    tweetList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
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