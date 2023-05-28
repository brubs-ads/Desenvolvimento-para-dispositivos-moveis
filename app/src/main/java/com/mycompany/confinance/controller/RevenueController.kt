package com.mycompany.confinance.controller

import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.repository.MovementRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Session

class RevenueController {

    private val repository = MovementRepository()

    fun getMovementUserId(onSuccess : (List<GetMovementModel>) -> Unit, onFailure: (message: String) -> Unit) {
        repository.getMovementByUserId(Session.userId!!, object :
            ApiListener<List<GetMovementModel>> {
            override fun onSuccess(result: List<GetMovementModel>) {
                if (result.isNotEmpty()){
                    onSuccess(result)
                }
            }

            override fun onFailure(message: String) {
                onFailure(message)
            }

        })
    }


}