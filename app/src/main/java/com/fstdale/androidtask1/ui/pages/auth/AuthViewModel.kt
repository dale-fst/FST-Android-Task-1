package com.fstdale.androidtask1.ui.pages.auth

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fstdale.androidtask1.App
import com.fstdale.androidtask1.R
import com.fstdale.androidtask1.data.models.User
import com.fstdale.androidtask1.data.repositories.UserRepository
import com.fstdale.androidtask1.utils.Constant
import com.fstdale.androidtask1.utils.interfaces.AuthCallback
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var firstname: String? = null
    var lastname: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null
    val progress = MutableLiveData(false)
    val error = MutableLiveData("")
    var authCallback: AuthCallback? = null

    val user by lazy {
        repository.currentUser()
    }

    fun login() {
        error.postValue("")

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            error.postValue(App.resourses.getString(R.string.error_login_complete_fields))
            return
        }

        progress.postValue(true)

        viewModelScope.launch {
            try {
                repository.login(email!!, password!!)
                if(repository.currentUser() != null)
                    authCallback?.onFinish()
                else
                    errorMessageAuth()
            } catch(er: Exception) {
                error.postValue(er.message!!)
                progress.postValue(false)
            }
        }
    }

    fun signup() {
        error.postValue("")

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

        viewModelScope.launch {
            try {
                repository.register(email!!, password!!)
                val currentUser = repository.currentUser()
                if(currentUser != null) {
                    repository.createUser(User(firstname, lastname, email, currentUser.uid))
                    authCallback?.onFinish()
                } else
                    errorMessageAuth()
            } catch(er: Exception) {
                error.postValue(er.message!!)
                progress.postValue(false)
            }
        }
    }

    private fun errorMessageAuth() {
        throw FirebaseAuthException(
            Constant.ERROR_AUTH,
            App.resourses.getString(R.string.error_failed))
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

    private tailrec fun Context.getActivity(): Activity? = this as? Activity
        ?: (this as? ContextWrapper)?.baseContext?.getActivity()
}