package com.fstdale.androidtask1.ui.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.ui.pages.feeds.FeedsFragment
import com.fstdale.androidtask1.ui.pages.home.HomeFragment
import com.fstdale.androidtask1.ui.pages.home.HomeViewModel
import com.fstdale.androidtask1.ui.pages.others.OthersFragment
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

        replaceFragment(homeFragment)
        homeViewModel.setURL()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_feeds -> replaceFragment(feedsFragment)
                R.id.ic_others -> replaceFragment(otherFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}