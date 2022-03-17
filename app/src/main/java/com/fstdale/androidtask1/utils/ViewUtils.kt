package com.fstdale.androidtask1.utils

import android.content.Context
import android.content.Intent
import com.fstdale.androidtask1.ui.views.MainActivity
import com.fstdale.androidtask1.ui.views.login.LoginActivity

class ViewUtils {
    fun Context.startHomeActivity() =
        Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }

    fun Context.startLoginActivity() =
        Intent(this, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
}