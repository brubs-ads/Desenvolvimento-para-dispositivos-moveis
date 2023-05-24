package com.mycompany.confinance.controller

import com.mycompany.confinance.model.user.GetUserModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Session


class UserProfileController {
    private var userRepository = UserRepository()
    fun getUser(onSuccess: (name: String, email: String) -> Unit,onFailure: (message: String) -> Unit){
        userRepository.getUserById(id= Session.userId!!, object : ApiListener<GetUserModel>{
            override fun onSuccess(result: GetUserModel) {
                    onSuccess(result.name, result.email)
            }

            override fun onFailure(message: String) {
                onFailure(message)
            }

        })
    }
}