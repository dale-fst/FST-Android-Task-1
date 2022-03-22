package com.fstdale.androidtask1.ui.pages.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fstdale.androidtask1.data.repositories.TweetRepository

@Suppress("UNCHECKED_CAST")
class FeedsViewModelFactory(private val repository: TweetRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedsViewModel(repository) as T
    }
}