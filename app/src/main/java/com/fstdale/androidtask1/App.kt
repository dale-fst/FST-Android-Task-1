package com.fstdale.androidtask1

import android.app.Application
import android.content.res.Resources
import com.fstdale.androidtask1.data.db.FirestoreDatabase
import com.fstdale.androidtask1.data.firebase.FirebaseSource
import com.fstdale.androidtask1.data.repositories.UserRepository
import com.fstdale.androidtask1.ui.factories.AuthViewModelFactory
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

        bind() from singleton { FirestoreDatabase(instance()) }
        bind() from singleton { instance<FirestoreDatabase>().noteDao() }
        bind() from singleton { FirebaseFirestore.getInstance() }
        bind() from singleton { FirebaseSource(instance(), instance())}
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
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