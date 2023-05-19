package com.mycompany.confinance.controller

import com.mycompany.confinance.model.user.UserLoginModel
import com.mycompany.confinance.model.validation.ValidationModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Constants

class LoginController{

    private val userRepository = UserRepository()
    private var validationModel = ValidationModel()
    companion object {
        private var userId: Long? = null
      private  fun setUserId(userId: Long?) {
            this.userId = userId
        }

        fun getIdUser(): Long? {
            return userId
        }
    }

    fun login(email: String, password: String) : ValidationModel {
        if (email.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) && password.length >= 6) {
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
        }else{
            validationModel.status()
            validationModel.setValidationMessage("Parâmetros Incosistentes, Verifique.")
        }
        return validationModel
    }
}