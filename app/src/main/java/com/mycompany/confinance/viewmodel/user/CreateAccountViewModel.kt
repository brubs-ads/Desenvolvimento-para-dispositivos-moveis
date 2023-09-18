package com.mycompany.confinance.viewmodel.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CreateAccountViewModel(application: Application) : AndroidViewModel(application) {

    //    private val user = UserRepository()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun createAccount(name: String?, email: String?, password: String?) {

        if (name != null && password!!.length >= 6) {
            _isLoading.value = true
        } else {
            _isLoading.value = false
        }

    }

}