package com.mycompany.confinance.controller

import com.mycompany.confinance.model.user.GetUserModel
import com.mycompany.confinance.model.user.ResponseUserModel
import com.mycompany.confinance.model.validation.ValidationModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener

class UserProfileController {
    private var userRepository = UserRepository()
    private lateinit var userData : ResponseUserModel
    private var id: Long = 10



    fun getUser(){
        userRepository.getUserById(id, object : ApiListener<GetUserModel>{
            override fun onSuccess(result: GetUserModel) {
                userData.setName(result.name)
                userData.setEmail(result.email)
            }

            override fun onFailure(message: String) {
                val s = ""
            }

        })
    }
}