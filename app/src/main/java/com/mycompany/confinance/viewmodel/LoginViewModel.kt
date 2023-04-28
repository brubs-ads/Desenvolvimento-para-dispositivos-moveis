package com.mycompany.confinance.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    fun doLogin(email: String, password: String) {
        if (email != "" && password != ""){
            TODO("Not yet implemented")

        }else{
            Toast.makeText(getApplication(),"ERRO", Toast.LENGTH_SHORT).show()
        }

    }
}