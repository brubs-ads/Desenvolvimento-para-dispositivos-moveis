package com.mycompany.confinance.service

import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface MovementService {
    @POST(Constants.HTTP.URL.URL_CREATE_MOVEMENT)
    fun createMoviment(@Body movement: MovementModel): Call<ResponseModel>

    @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_ID_USER)
    fun getMovement(@Path("id") id: Long, @Query("type") type: String): Call<List<MovementModel>>

    @DELETE(Constants.HTTP.URL.URL_DELETE_MOVEMENT)
    fun deleteMovement(@Path("id") id: Long): Call<ResponseModel>

    @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_ID)
    fun getMovementById(@Path("id") id: Long):Call<MovementModel>

}