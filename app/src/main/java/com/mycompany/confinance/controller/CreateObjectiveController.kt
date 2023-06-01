package com.mycompany.confinance.controller

import com.mycompany.confinance.model.objective.ObjectiveModel
import com.mycompany.confinance.repository.ObjectiveRepository
import com.mycompany.confinance.repository.listener.ApiListener

class CreateObjectiveController {

    private val repository = ObjectiveRepository()

    fun createObjective(
        name: String,
        value: Double,
        data: String,
        onSuccess: () -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        if (name != "" && value <= 0) {
            repository.createObjective(
                value = value,
                name = name,
                data = data,
                object : ApiListener<ObjectiveModel> {
                    override fun onSuccess(result: ObjectiveModel) {
                        onSuccess.invoke()
                    }

                    override fun onFailure(message: String) {
                        onFailure.invoke(message)
                    }
                })
        } else {
            onFailure("parâmetros inconsistente.")
        }
    }


}