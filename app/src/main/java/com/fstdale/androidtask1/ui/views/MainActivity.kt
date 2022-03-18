package com.fstdale.androidtask1.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.ui.views.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: Need to remove logic once not needed.
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}