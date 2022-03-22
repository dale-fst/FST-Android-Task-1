package com.fstdale.androidtask1

import android.app.Application
import android.content.res.Resources
import com.fstdale.androidtask1.data.repositories.TweetRepository
import com.fstdale.androidtask1.data.services.RetrofitService
import com.fstdale.androidtask1.ui.pages.feeds.FeedsViewModelFactory
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
        bind() from singleton { TweetRepository(instance(), instance()) }
        bind() from provider { FeedsViewModelFactory(instance()) }
    }

    companion object {
        lateinit var instance: Application
        lateinit var resourses: Resources
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        resourses = resources
    }
}