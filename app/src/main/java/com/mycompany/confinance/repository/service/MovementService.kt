package com.mycompany.confinance.repository.service

import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.util.Constants
import retrofit2.Response
import retrofit2.http.*

interface MovementService{

        @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_LIST)
        fun getAllMovements(): Response<List<MovementModel>>

        @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_ID)
        fun getMovementById(@Path("id") id: Long): Response<MovementModel>

        @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_ID_USER)
        fun getMovementByUserId(@Path("userId") userId: Long): Response<MovementModel>

        @POST(Constants.HTTP.URL.URL_CREATE_MOVEMENT)
        fun createMovement(@Body movement: MovementModel): Response<MovementModel>

        @PUT(Constants.HTTP.URL.URL_UPDATE_MOVEMENT)
        fun updateMovementById(@Path("id") id: Long, @Body movement: MovementModel): Response<MovementModel>

        @DELETE(Constants.HTTP.URL.URL_DELETE_MOVEMENT)
        fun deleteMovementById(@Path("id") id: Long): Response<Unit>


    }
