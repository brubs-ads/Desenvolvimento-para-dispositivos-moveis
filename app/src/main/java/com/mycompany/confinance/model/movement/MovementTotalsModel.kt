package com.mycompany.confinance.model.movement

import com.mycompany.confinance.model.user.UserTeste

data class MovementTotalsModel(
    val totalRevenues: Double,
    val totalExpenses: Double,
    val total: Double,
    val user: UserTeste
)

