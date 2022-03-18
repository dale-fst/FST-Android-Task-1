package com.fstdale.androidtask1.ui.viewmodels

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.fstdale.androidtask1.App
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.data.repositories.UserRepository
import com.fstdale.androidtask1.ui.interfaces.AuthListener
import com.fstdale.androidtask1.ui.views.login.LoginActivity
import com.fstdale.androidtask1.ui.views.signin.SignupActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var firstname: String? = null
    var lastname: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null
    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    fun login() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure(App.resourses.getString(R.string.error_login_incorrect))
            return
        }

        authListener?.onStarted()

        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    fun signup() {
        if (
            firstname.isNullOrEmpty() ||
            lastname.isNullOrEmpty() ||
            email.isNullOrEmpty() ||
            password.isNullOrEmpty() ||
            passwordConfirm.isNullOrEmpty()) {
            authListener?.onFailure(App.resourses.getString(R.string.error_signup_complete_fields))
            return
        }

        if(password!!.length < 6) {
            authListener?.onFailure(App.resourses.getString(R.string.error_signup_password_length))
            return
        }

        if(!password.equals(passwordConfirm)) {
            authListener?.onFailure(App.resourses.getString(R.string.error_signup_password_confirm))
            return
        }

        authListener?.onStarted()

        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    fun goToSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}