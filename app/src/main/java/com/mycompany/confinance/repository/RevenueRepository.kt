package com.mycompany.confinance.repository

import com.mycompany.confinance.request.Retrofit
import com.mycompany.confinance.service.MovementService

class RevenueRepository() {

    private val remote = Retrofit.getService(MovementService::class.java)


    fun createMovement(
        value: Long,
        description: String,
        data: String,
        fixedIncome: Boolean?,
        repetitions: Int?,
        category: String
    ) {
    }

}