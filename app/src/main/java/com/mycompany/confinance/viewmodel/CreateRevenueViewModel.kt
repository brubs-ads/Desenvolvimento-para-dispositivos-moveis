package com.mycompany.confinance.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.repository.RevenueRepository
import com.mycompany.confinance.request.ApiListener

class CreateRevenueViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = RevenueRepository()

    fun createRevenue(
        value: Long,
        description: String,
        data: String,
        fixedIncome: Boolean?,
        repetitions: String?,
        category: Int
    ){
        if (value != 0.0.toLong() && description != "" && data != null) {
            if (fixedIncome == false) {
                repository.createMovement(
                    context = application,
                    codeType = 1,
                    value = value,
                    description = description,
                    fixedIncome = null,
                    data = data,
                    repetitions = repetitions,
                    category = category,
                    listener = object : ApiListener<ResponseModel> {
                        override fun onSuccess(result: ResponseModel) {
                            val s = result
                        }

                        override fun onFailure(message: String, code: Int) {
                            val s = message
                            val t = code
                        }

                    }
                )
            } else {
                repository.createMovement(
                    context = application,
                    codeType = 1,
                    value = value,
                    description = description,
                    data = data,
                    fixedIncome = fixedIncome,
                    repetitions = null,
                    category = category,
                    listener = object : ApiListener<ResponseModel> {
                        override fun onSuccess(result: ResponseModel) {
                            val s = result
                        }

                        override fun onFailure(message: String, code: Int) {
                            val s = message
                            val t = code
                        }

                    }
                )
            }
        }
    }
}