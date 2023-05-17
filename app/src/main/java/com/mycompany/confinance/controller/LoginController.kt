package com.mycompany.confinance.controller

import com.mycompany.confinance.model.UserLoginModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener

class LoginController{

    private val userRepository = UserRepository()

    fun login(email: String, password: String) {
        if (email != null && password !=null){
            userRepository.login(email,password, object : ApiListener<UserLoginModel>{
                override fun onSuccess(result: UserLoginModel) {

                }

                override fun onFailure(message: String) {
                }
            })
        }
    }
}