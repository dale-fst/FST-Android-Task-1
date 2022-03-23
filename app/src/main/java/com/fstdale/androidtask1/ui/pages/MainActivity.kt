package com.fstdale.androidtask1.ui.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.ui.pages.auth.LoginActivity
import com.fstdale.androidtask1.ui.pages.feeds.FeedsFragment
import com.fstdale.androidtask1.ui.pages.home.HomeFragment
import com.fstdale.androidtask1.ui.pages.home.HomeViewModel
import com.fstdale.androidtask1.ui.pages.others.OthersFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val feedsFragment = FeedsFragment()
    private val otherFragment = OthersFragment()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        homeViewModel.setURL()
        replaceFragment(homeFragment)

        if(!isLoggedIn())
            openLogin()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_feeds -> replaceFragment(feedsFragment)
                R.id.ic_others -> {
                    if(isLoggedIn())
                        replaceFragment(otherFragment)
                    else
                        openLogin()
                }
            }
            true
        }
    }

    private fun isLoggedIn() = FirebaseAuth.getInstance().currentUser != null

    private fun openLogin() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}