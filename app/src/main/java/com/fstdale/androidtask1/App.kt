package com.fstdale.androidtask1

import android.app.Application
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import com.fstdale.androidtask1.data.firebase.FirebaseSource
import com.fstdale.androidtask1.data.repositories.TweetRepository
import com.fstdale.androidtask1.data.repositories.UserRepository
import com.fstdale.androidtask1.data.services.RetrofitService
import com.fstdale.androidtask1.ui.pages.auth.AuthViewModelFactory
import com.fstdale.androidtask1.ui.pages.feeds.FeedsViewModelFactory
import com.fstdale.androidtask1.ui.pages.others.OthersViewModelFactory
import com.google.firebase.firestore.FirebaseFirestore
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { FirebaseFirestore.getInstance() }
        bind() from singleton { RetrofitService.getInstance() }
        bind() from singleton { FirebaseSource(instance()) }
        bind() from singleton { TweetRepository(instance(), instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { FeedsViewModelFactory(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { OthersViewModelFactory(instance()) }
    }

    companion object {
        lateinit var instance: Application
        lateinit var resourses: Resources
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        instance = this
        resourses = resources
    }
}