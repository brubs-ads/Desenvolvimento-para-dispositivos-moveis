package com.mycompany.confinance.model.user

class ResponseUserModel{

    private var name: String = ""
    private var email: String = ""

    fun setName(name: String) {
        this.name = name
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getName() = name
    fun getEmail() = email

}