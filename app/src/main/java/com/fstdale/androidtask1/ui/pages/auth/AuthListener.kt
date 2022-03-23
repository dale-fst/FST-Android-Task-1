package com.fstdale.androidtask1.ui.pages.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}