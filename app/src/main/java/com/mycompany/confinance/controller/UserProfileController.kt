package com.mycompany.confinance.controller

import com.mycompany.confinance.model.user.GetUserModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener

class UserProfileController {
    private var userRepository = UserRepository()
    private val login = LoginController.getIdUser()
    private val create = CreateAccountController.getIdUser()
    private var id: Long = 0

    private fun resultId(){
        if(login != null){
            id = login
        }else if (create != null){
            id = create
        }
    }



    fun getUser(){
        resultId()
        userRepository.getUserById(id, object : ApiListener<GetUserModel>{
            override fun onSuccess(result: GetUserModel) {
                val s = ""

            }

            override fun onFailure(message: String) {
                val s = ""
            }

        })
    }
}