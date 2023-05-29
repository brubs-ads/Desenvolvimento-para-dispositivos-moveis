package com.mycompany.confinance.controller

import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.repository.MovementRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.util.Session

class RevenueController {

    private val repository = MovementRepository()

    fun getMovementUserId(
        onSuccess: (List<GetMovementModel>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        repository.getMovementByUserId(Session.userId!!, Constants.MOVEMENT.REVENUE, object :
            ApiListener<List<GetMovementModel>> {
            override fun onSuccess(result: List<GetMovementModel>) {
                if (result.isNotEmpty()) {
                    onSuccess(result)
                }
            }

            override fun onFailure(message: String) {
                onFailure(message)
            }

        })
    }

    fun deleteMovementById(
        id: Long?,
        onSuccess: () -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        if (id != null) {
            repository.deleteMovementById(id, object : ApiListener<Unit> {
                override fun onSuccess(result: Unit) {
                    onSuccess()
                }

                override fun onFailure(message: String) {
                    onFailure(message)
                }
            })
        }
    }
}


