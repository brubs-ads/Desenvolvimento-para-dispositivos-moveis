package com.mycompany.confinance.controller

import com.mycompany.confinance.util.Session
import com.mycompany.confinance.model.user.ResponseUserModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Constants
import java.net.HttpURLConnection

class LoginController{

    private val userRepository = UserRepository()

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (message: String) -> Unit) {
        if (email.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) && password.length >= 6) {
            userRepository.login(email, password, object : ApiListener<ResponseUserModel> {
                override fun onSuccess(result: ResponseUserModel) {
                    if (result.status == HttpURLConnection.HTTP_OK) {
                        Session.userId = result.userId
                        onSuccess.invoke()
                    }else{
                        onFailure.invoke(result.message)
                    }
                }

                override fun onFailure(message: String) {
                    onFailure.invoke(message)
                }
            })
        }else{
            onFailure.invoke("Parâmetros Incosistentes, Verifique.")
        }
    }
}