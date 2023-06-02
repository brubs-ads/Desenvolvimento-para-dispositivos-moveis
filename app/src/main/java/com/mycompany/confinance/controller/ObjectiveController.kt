package com.mycompany.confinance.controller

import com.mycompany.confinance.model.objective.ObjectiveModel
import com.mycompany.confinance.repository.ObjectiveRepository
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.util.Session

class ObjectiveController {

    private val repository = ObjectiveRepository()


    fun getObjective(onSuccess:(list: List<ObjectiveModel>) -> Unit, onFailure:(message:String) -> Unit) {
        repository.getObjectiveById(Session.userId!!, object : ApiListener<List<ObjectiveModel>> {
            override fun onSuccess(result: List<ObjectiveModel>) {
                if (result.isNotEmpty()) {
                    onSuccess.invoke(result)
                }
            }

            override fun onFailure(message: String) {
                onFailure(message)
            }
        })
    }
}