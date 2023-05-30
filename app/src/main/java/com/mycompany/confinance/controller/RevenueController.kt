package com.mycompany.confinance.controller

import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.model.movement.MovementResponse
import com.mycompany.confinance.model.user.ResponseUserModel
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

    fun deleteMovementById(id:Long, result: (message: String, status: Boolean) -> Unit){
        repository.deleteMovementById(id, object : ApiListener<MovementResponse> {
            override fun onSuccess(result: MovementResponse) {
                result(result.message,true)
            }
            override fun onFailure(message: String) {
                result(message,false)
            }
        })
    }
}


