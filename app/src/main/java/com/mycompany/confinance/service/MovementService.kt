package com.mycompany.confinance.service

import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.POST

interface MovementService {
    @POST(Constants.HTTP.URL.URL_CREATE_MOVEMENT)
    fun createMoviment(@Body movement: MovementModel): Call<ResponseModel>
}