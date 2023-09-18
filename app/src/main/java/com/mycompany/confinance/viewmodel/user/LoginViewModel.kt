package com.mycompany.confinance.viewmodel.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    //    private val user = UserRepository()
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun login(email:String? , password: String?) {
        if (email !=null && password != null){
            _isLoading.value = true
        }else{
            _isLoading.value = false
        }
    }
}