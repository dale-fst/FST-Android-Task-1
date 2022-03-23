package com.fstdale.androidtask1.ui.pages.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.databinding.ActivitySignupBinding
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by closestKodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
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
        progressbar.visibility = View.VISIBLE
        signup.visibility = View.GONE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        Toast.makeText(this, getString(R.string.account_created), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        signup.visibility = View.VISIBLE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}