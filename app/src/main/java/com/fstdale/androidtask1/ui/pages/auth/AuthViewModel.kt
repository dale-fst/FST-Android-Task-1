package com.fstdale.androidtask1.ui.pages.auth

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fstdale.androidtask1.App
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.data.models.User
import com.fstdale.androidtask1.data.repositories.UserRepository
import com.fstdale.androidtask1.ui.pages.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var firstname: String? = null
    var lastname: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null
    val progress = MutableLiveData(false)
    val error = MutableLiveData("")

    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    fun login() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            error.postValue(App.resourses.getString(R.string.error_login_complete_fields))
            return
        }

        progress.postValue(true)

        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                goToMain()
            }, {
                error.postValue(it.message!!)
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
            error.postValue(App.resourses.getString(R.string.error_signup_complete_fields))
            return
        }

        if(password!!.length < 6) {
            error.postValue(App.resourses.getString(R.string.error_signup_password_length))
            return
        }

        if(!password.equals(passwordConfirm)) {
            error.postValue(App.resourses.getString(R.string.error_signup_password_confirm))
            return
        }

        progress.postValue(true)

        val user = User(firstname, lastname, email)
        val disposable = repository.register(user, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                goToMain()
            }, {
                error.postValue(it.message!!)
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

    fun goToMain() {
//        Intent(view.context, MainActivity::class.java).also {
//            view.context.startActivity(it)
//        }
//        view.context.getActivity()?.finish()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    private tailrec fun Context.getActivity(): Activity? = this as? Activity
        ?: (this as? ContextWrapper)?.baseContext?.getActivity()
}