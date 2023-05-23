package com.mycompany.confinance.controller

import com.mycompany.confinance.util.Session
import com.mycompany.confinance.model.user.CreateUserModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener

class CreateAccountController {

    private val repository = UserRepository()

    fun createAccount(name: String, email: String, password: String,onSuccess: () -> Unit, onFailure: (message: String) -> Unit) {
        if (name.length in 3..30 && email.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            && password.length >=8) {
            repository.createAccount(name,email,password, object : ApiListener<CreateUserModel>{
                override fun onSuccess(result: CreateUserModel) {
                    Session.userId = result.id
                    onSuccess.invoke()
                }
                override fun onFailure(message: String) {
                    onFailure(message)
                }
            })
        }else{
            onFailure("Erro, entre em contato com os Desenvolvedores.")
        }
    }
}