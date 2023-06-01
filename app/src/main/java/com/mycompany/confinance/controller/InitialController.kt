package com.mycompany.confinance.controller

import com.mycompany.confinance.model.movement.MovementTotalsModel
import com.mycompany.confinance.repository.MovementRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Session

class InitialController {
    private val repository = MovementRepository()

    fun getTotal(onSuccess: (model: MovementTotalsModel) -> Unit, onFailure: (message: String) -> Unit) {
        repository.getTotalRevenueAndExpense(Session.userId!!,object : ApiListener<MovementTotalsModel> {
            override fun onSuccess(result: MovementTotalsModel) {
                onSuccess(result)

            }

            override fun onFailure(message: String) {
                onFailure(message)
            }

        })
    }

}