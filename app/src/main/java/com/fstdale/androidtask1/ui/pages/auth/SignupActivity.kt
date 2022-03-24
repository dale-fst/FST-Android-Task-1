package com.fstdale.androidtask1.ui.pages.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.databinding.ActivitySignupBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by closestKodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        viewModel.authListener = this
        if(viewModel.user != null) {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onStarted() {
        viewModel.progress.postValue(true)
    }

    override fun onSuccess() {
        Toast.makeText(this, getString(R.string.account_created), Toast.LENGTH_SHORT).show()
        viewModel.goToMain(binding.root)
    }

    override fun onFailure(message: String) {
        viewModel.progress.postValue(false)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}