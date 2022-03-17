package com.fstdale.androidtask1

import android.app.Application
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
        bind() from singleton { FirebaseSource()}
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }
}