package com.fstdale.androidtask1.ui.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.databinding.ActivityMainBinding
import com.fstdale.androidtask1.ui.pages.home.HomeFragment
import com.fstdale.androidtask1.ui.pages.home.HomeViewModel
import com.fstdale.androidtask1.utils.interfaces.MenuCallback
import com.google.firebase.auth.FirebaseAuth
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class MainActivity : AppCompatActivity(), MenuCallback, KodeinAware {

    override val kodein by closestKodein()
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.menuCallback = this
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        homeViewModel.setURL()
        replaceFragment(HomeFragment())

        if(!isLoggedIn())
            viewModel.goToLogin(binding.root)
    }

    private fun isLoggedIn() = FirebaseAuth.getInstance().currentUser != null

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onFinish(fragment: Fragment) {
        replaceFragment(fragment)
    }
}