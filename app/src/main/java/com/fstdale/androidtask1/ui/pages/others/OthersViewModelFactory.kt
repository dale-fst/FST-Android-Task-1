package com.fstdale.androidtask1.ui.pages.others

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fstdale.androidtask1.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class OthersViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OthersViewModel(repository) as T
    }
}