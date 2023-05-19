package com.mycompany.confinance.model.validation

class ValidationModel {
    private var status: Boolean = false
    private var validationMessage: String = ""

    fun setStatus(status: Boolean) {
        this.status = status
    }

    fun setValidationMessage(message: String) {
        this.validationMessage = message
    }

    fun status() = status
    fun message() = validationMessage
}
