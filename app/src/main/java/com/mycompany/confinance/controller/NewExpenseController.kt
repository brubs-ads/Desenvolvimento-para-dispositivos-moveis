package com.mycompany.confinance.controller

import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.model.user.UserTeste
import com.mycompany.confinance.repository.MovementRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.util.Session

class NewExpenseController {

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
                Constants.MOVEMENT.EXPENSE,
                value,
                description,
                date,
                UserTeste(Session.userId),
                object : ApiListener<CreateMovementModel> {
                    override fun onSuccess(result: CreateMovementModel) {
                        onSuccess.invoke()
                    }

                    override fun onFailure(message: String) {
                        onFailure(message)
                    }
                }
            )
        } else {
            onFailure("Erro ao criar Movimentação!")
        }
    }
    fun updateMovement(
        id: Long,
        value: Double,
        description: String,
        date: String,
        onSuccess: () -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        if (value > 0 && description.isNotBlank()) {
            repository.updateMovement(
                id,
                Constants.MOVEMENT.EXPENSE,
                value,
                description,
                date,
                object : ApiListener<GetMovementModel> {
                    override fun onSuccess(result: GetMovementModel) {
                        onSuccess.invoke()
                    }

                    override fun onFailure(message: String) {
                        onFailure(message)
                    }
                }
            )
        } else {
            onFailure("Erro ao atualizar Movimentação!")
        }
    }

}
