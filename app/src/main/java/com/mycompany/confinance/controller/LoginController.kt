package com.mycompany.confinance.controller

import android.content.Context
import android.view.View
import android.widget.Toast

class LoginController(private val view: View){

    fun login(email: String, password: String) {
        if (email != null && password != null) {
            //val intent = Intent()
        }else{
            Toast.makeText(view as Context, "Digite o usuário e senha", Toast.LENGTH_SHORT).show()
        }
    }
}