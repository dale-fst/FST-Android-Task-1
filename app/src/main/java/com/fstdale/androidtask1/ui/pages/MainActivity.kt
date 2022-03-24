package com.fstdale.androidtask1.ui.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.databinding.ActivityMainBinding
import com.fstdale.androidtask1.ui.pages.auth.AuthViewModel
import com.fstdale.androidtask1.ui.pages.auth.AuthViewModelFactory
import com.fstdale.androidtask1.ui.pages.feeds.FeedsFragment
import com.fstdale.androidtask1.ui.pages.home.HomeFragment
import com.fstdale.androidtask1.ui.pages.home.HomeViewModel
import com.fstdale.androidtask1.ui.pages.others.OthersFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel

    private val homeFragment = HomeFragment()
    private val feedsFragment = FeedsFragment()
    private val otherFragment = OthersFragment()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        installSplashScreen()
        homeViewModel.setURL()
        replaceFragment(homeFragment)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        if(!isLoggedIn())
            viewModel.goToLogin(binding.root)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_feeds -> replaceFragment(feedsFragment)
                R.id.ic_others -> {
                    if(isLoggedIn())
                        replaceFragment(otherFragment)
                    else
                        viewModel.goToLogin(binding.root)
                }
            }
            true
        }
    }

    private fun isLoggedIn() = FirebaseAuth.getInstance().currentUser != null

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}