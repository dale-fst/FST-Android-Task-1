package com.fstdale.androidtask1.ui.views.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.databinding.ActivitySignupBinding
import com.fstdale.androidtask1.ui.factories.AuthViewModelFactory
import com.fstdale.androidtask1.ui.interfaces.AuthListener
import com.fstdale.androidtask1.ui.viewmodels.AuthViewModel
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    //override val kodein by kodein()
    override val kodein by closestKodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
        signup.visibility = View.GONE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        // TODO: logic after logging in.
        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        signup.visibility = View.VISIBLE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}