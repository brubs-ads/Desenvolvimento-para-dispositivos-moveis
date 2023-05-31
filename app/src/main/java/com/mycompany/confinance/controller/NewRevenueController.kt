package com.mycompany.confinance.controller

import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.model.user.UserTeste
import com.mycompany.confinance.repository.MovementRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.util.Session

class NewRevenueController {
    private val repository = MovementRepository()

    fun createMovement(
        value: Double,
        description: String,
        date: String,
        onSuccess: () -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        if (value >0 && description.isNotBlank()) {
            repository.createMovement(
                Constants.MOVEMENT.REVENUE,
                value,
                description,
                date,
                UserTeste(Session.userId),
                object : ApiListener<CreateMovementModel> {
                    override fun onSuccess(result: CreateMovementModel) {
                        Session.total += result.value
                        Session.movementId = result.id
                        onSuccess.invoke()
                    }

                    override fun onFailure(message: String) {
                        onFailure(message)
                    }
                }
            )
        } else {
            onFailure("Invalid value or description")
        }
    }

}