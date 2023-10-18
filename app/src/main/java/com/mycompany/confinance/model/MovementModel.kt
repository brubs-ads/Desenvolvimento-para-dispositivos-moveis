package com.mycompany.confinance.model

data class MovementModel(
    val type_movement: String,
    val value: Long,
    val description: String,
    val date: String,
    val user: User
)