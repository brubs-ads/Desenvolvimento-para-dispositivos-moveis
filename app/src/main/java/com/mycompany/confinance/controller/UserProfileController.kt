package com.mycompany.confinance.controller

import com.mycompany.confinance.model.user.GetUserModel
import com.mycompany.confinance.model.user.ResponseUserModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Session
import java.net.HttpURLConnection


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

    fun deleteUser(status: (message: String, code:Int) -> Unit) {
        userRepository.deleteUser(id = Session.userId!!, object : ApiListener<ResponseUserModel>{
            override fun onSuccess(result: ResponseUserModel) {
                status(result.message, result.status)
            }
            override fun onFailure(message: String) {
                status(message,0)
            }

        })
    }
}