package com.mycompany.confinance.controller

import com.mycompany.confinance.model.UserLoginModel
import com.mycompany.confinance.model.ValidationModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Constants

class LoginController{

    private val userRepository = UserRepository()
    private var validationModel = ValidationModel()
    companion object {
        private var userId: Int? = null
      private  fun setUserId(userId: Int?) {
            this.userId = userId
        }

        fun getIdUser(): Int? {
            return userId
        }
    }

    fun login(email: String, password: String) : ValidationModel {
        if (email != null && password != null) {
            userRepository.login(email, password, object : ApiListener<UserLoginModel> {
                override fun onSuccess(result: UserLoginModel) {
                    if (result.status == Constants.HTTP.CODE.SUCCESS) {
                        validationModel.setStatus(true)
                        setUserId(result.userId)
                    }else{
                        validationModel.setValidationMessage(result.message)
                        validationModel.status()
                    }
                }

                override fun onFailure(message: String) {
                    validationModel.status()
                    validationModel.setValidationMessage(message)
                }
            })
        }
        return validationModel
    }
}