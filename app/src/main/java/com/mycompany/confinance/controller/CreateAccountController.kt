package com.mycompany.confinance.controller

import com.mycompany.confinance.model.CreateUserModel
import com.mycompany.confinance.model.validation.ValidationModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.repository.listener.ApiListener

class CreateAccountController {

    private val repository = UserRepository()
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
    fun createAccount(name: String, email: String, password: String): ValidationModel {
        if (name.length in 3..30 && email.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            && password.length >=8) {
            repository.createAccount(name,email,password, object : ApiListener<CreateUserModel>{
                override fun onSuccess(result: CreateUserModel) {
                    validationModel.setStatus(true)
                    setUserId(result.id)
                }
                override fun onFailure(message: String) {
                    validationModel.setValidationMessage(message)
                    validationModel.status()
                }
            })
        }else{
            validationModel.setValidationMessage("Parâmetros Incosistentes, Verifique.")
            validationModel.status()
        }
        return  validationModel
    }
}