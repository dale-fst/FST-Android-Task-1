package com.fstdale.androidtask1.data.firebase

import com.fstdale.androidtask1.data.models.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import kotlinx.coroutines.tasks.await

class FirebaseSource(private val database: FirebaseFirestore) {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun getUserDetails() = database
        .collection("users")
        .document(firebaseAuth.uid!!)
        .get()

    suspend fun login(email: String, password: String): AuthResult {
        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

//    fun login(email: String, password: String) = Completable.create { emitter ->
//        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//            if (!emitter.isDisposed) {
//                if (it.isSuccessful)
//                    emitter.onComplete()
//                else
//                    emitter.onError(it.exception!!)
//            }
//        }
//    }

    suspend fun register(email: String, password: String): AuthResult {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun createUser(user: User) {
        database.collection("users").document(user.uid).set(user).await()
    }

//    fun register(user: User, password: String) = Completable.create { emitter ->
//        firebaseAuth.createUserWithEmailAndPassword(user.email!!, password).addOnCompleteListener {
//            if (!emitter.isDisposed) {
//                if (it.isSuccessful) {
//                    user.uid = firebaseAuth.uid!!
//                    database.collection("users")
//                        .document(user.uid)
//                        .set(user)
//                        .addOnSuccessListener {
//                            emitter.onComplete()
//                        }
//                }
//                else
//                    emitter.onError(it.exception!!)
//            }
//        }
//    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}