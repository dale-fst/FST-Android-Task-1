package com.fstdale.androidtask1.views.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fstdale.androidtask1.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SignupFragment())
                .commitNow()
        }
    }
}