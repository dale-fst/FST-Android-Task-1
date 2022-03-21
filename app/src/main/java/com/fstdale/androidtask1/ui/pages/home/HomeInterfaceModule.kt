package com.fstdale.androidtask1.ui.pages.home

import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView

class HomeInterfaceModule(
    private val homeViewModel: HomeViewModel,
    private val context: Context,
    val webView: WebView,
) {
    @JavascriptInterface
    fun signup() {

    }

    @JavascriptInterface
    fun getUserLoginStatus() {
    }
}