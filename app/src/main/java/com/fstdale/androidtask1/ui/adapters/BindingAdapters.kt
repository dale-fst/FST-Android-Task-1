package com.fstdale.androidtask1.ui.adapters

import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class BindingAdapters {
    companion object{
        @JvmStatic
        @BindingAdapter("onNavigationItemSelected")
        fun setOnNavigationItemSelectedListener(view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener?) {
            view.setOnNavigationItemSelectedListener(listener)
        }
    }
}