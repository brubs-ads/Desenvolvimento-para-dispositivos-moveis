package com.mycompany.confinance.repository

import com.mycompany.confinance.repository.service.MovementService

class MovementRepository {

    private val remote = RetrofitClient.getService(MovementService::class.java)
}