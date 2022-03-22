package com.fstdale.androidtask1.ui.pages.auth

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.fstdale.androidtask1.App
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.data.models.User
import com.fstdale.androidtask1.data.repositories.UserRepository
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
            authListener?.onFailure(App.resourses.getString(R.string.error_login_complete_fields))
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

        val user = User(firstname, lastname, email)
        val disposable = repository.register(user, password!!)
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
        view.context.getActivity()?.finish()
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
        view.context.getActivity()?.finish()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    private tailrec fun Context.getActivity(): Activity? = this as? Activity
        ?: (this as? ContextWrapper)?.baseContext?.getActivity()
}