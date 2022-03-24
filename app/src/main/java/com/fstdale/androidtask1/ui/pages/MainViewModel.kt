package com.fstdale.androidtask1.ui.pages

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.ui.pages.auth.LoginActivity
import com.fstdale.androidtask1.ui.pages.feeds.FeedsFragment
import com.fstdale.androidtask1.ui.pages.home.HomeFragment
import com.fstdale.androidtask1.ui.pages.others.OthersFragment

class MainViewModel(val app: Application) : AndroidViewModel(app) {

    private val homeFragment = HomeFragment()
    private val feedsFragment = FeedsFragment()
    private val otherFragment = OthersFragment()
    var menuCallback: MenuCallback? = null

    fun setOnNavigationItemSelectedListener(@NonNull item: MenuItem) : Boolean {
        when(item.itemId) {
            R.id.ic_home -> menuCallback?.onFinish(homeFragment)
            R.id.ic_feeds -> menuCallback?.onFinish(feedsFragment)
            R.id.ic_others -> menuCallback?.onFinish(otherFragment)
        }
        return true
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
        view.context.getActivity()?.finish()
    }

    private tailrec fun Context.getActivity(): Activity? = this as? Activity
        ?: (this as? ContextWrapper)?.baseContext?.getActivity()
}