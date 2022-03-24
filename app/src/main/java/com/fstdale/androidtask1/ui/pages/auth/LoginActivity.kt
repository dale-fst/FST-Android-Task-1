package com.fstdale.androidtask1.ui.pages.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.databinding.ActivityLoginBinding
import com.fstdale.androidtask1.ui.pages.MainActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthCallback, KodeinAware {

    override val kodein by closestKodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        viewModel.authCallback = this
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        if(viewModel.user != null)
            finish()
    }

    override fun onFinish() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }
}