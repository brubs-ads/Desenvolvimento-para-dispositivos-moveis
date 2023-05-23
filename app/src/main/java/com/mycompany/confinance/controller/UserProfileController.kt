package com.mycompany.confinance.controller

import com.mycompany.confinance.util.Session
import com.mycompany.confinance.model.user.GetUserModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener

class UserProfileController {
    private var userRepository = UserRepository()
    fun getUser(){
        Session.userId

        userRepository.getUserById(id= Session.userId!!, object : ApiListener<GetUserModel>{
            override fun onSuccess(result: GetUserModel) {
                val s = ""

            }

            override fun onFailure(message: String) {
                val s = ""
            }

        })
    }
}