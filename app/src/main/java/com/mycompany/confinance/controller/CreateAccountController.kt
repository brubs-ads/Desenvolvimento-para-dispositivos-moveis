package com.mycompany.confinance.controller

import android.content.Context
import android.view.View
import android.widget.Toast

class CreateAccountController(private val view: View) {
    fun createAccount(email: String, password: String) {
        if (email != null && password != null){

        }else{
            Toast.makeText(view as Context , "Erro",Toast.LENGTH_SHORT).show()
        }
    }
}