package com.mycompany.confinance.controller

import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.model.movement.MovementResponse
import com.mycompany.confinance.repository.MovementRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Session
import java.util.Date

class MovementController {

    private val repository = MovementRepository()

    fun getAllMovements(listener: ApiListener<List<GetMovementModel>>) {
        repository.getAllMovements(listener)

    }

    fun getMovementById(id: Long, listener: ApiListener<GetMovementModel>) {
        repository.getMovementById(id, listener)
    }

    fun createMovement(
        type_movement: String,
        value: Double,
        description: String,
        date: Date,
        OnSucess: () -> Unit,
        OnFailure: (message: String) -> Unit
    ) {
        repository.createMovement(
            type_movement,
            value,
            description,
            date,
            object : ApiListener<CreateMovementModel> {
                override fun onSuccess(result: CreateMovementModel) {
                    Session.movementId = result.id
                    OnSucess.invoke()
                }

                override fun onFailure(message: String) {
                    OnFailure(message)
                }
            })



        fun deleteMovementById(status: (message: String, code: Int) -> Unit) {
            repository.deleteMovementById(
                id = Session.movementId!!,
                object : ApiListener<MovementResponse> {
                    override fun onSuccess(result: MovementResponse) {
                        status(result.message, result.status)
                    }

                    override fun onFailure(message: String) {
                        status(message, 0)
                    }

                })
        }
    }


}


