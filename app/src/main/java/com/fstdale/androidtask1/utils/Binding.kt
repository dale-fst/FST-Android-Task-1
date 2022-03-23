package com.fstdale.androidtask1.utils

import android.webkit.WebView
import androidx.databinding.BindingAdapter

object Binding {
    @JvmStatic
    @BindingAdapter("url")
    fun WebView.bindUrl(url: String?) {
        if (url != null) {
            loadUrl(url)
        }
    }
}