package com.mycompany.confinance.model

data class PasswordResetRequest(
    val currentPassword: String,
    val newPassword: String
)