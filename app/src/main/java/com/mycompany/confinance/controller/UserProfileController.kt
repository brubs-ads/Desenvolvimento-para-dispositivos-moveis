package com.mycompany.confinance.controller

import com.mycompany.confinance.model.validation.ValidationModel
import com.mycompany.confinance.repository.UserRepository

class UserProfileController {
    private val userRepository = UserRepository()
    private var validationModel = ValidationModel()

    fun getUser(){

    }

}