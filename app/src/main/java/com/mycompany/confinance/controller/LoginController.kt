package com.mycompany.confinance.controller

import com.mycompany.confinance.repository.UserRepository

class LoginController{

    private val userRepository = UserRepository()

    fun login(email: String, password: String) {
        if (email != null && password !=null){
            userRepository.login(email,password)
        }
    }
}