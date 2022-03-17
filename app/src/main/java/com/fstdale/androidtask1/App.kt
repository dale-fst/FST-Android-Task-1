package com.fstdale.androidtask1

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class App: Application(), KodeinAware {
    override val kodein: Kodein
        get() = Kodein.lazy {
            import(androidXModule(this@App))
            bind() from singleton { FirebaseFirestore.getInstance() }
        }
}