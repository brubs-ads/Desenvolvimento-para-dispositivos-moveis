package com.mycompany.confinance.repository.service

import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.model.movement.MovementResponse
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface MovementService{

        @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_LIST)
        fun getAllMovements() : Call<List<GetMovementModel>>

        @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_ID)
        fun getMovementById(@Path("id") id: Long) : Call<GetMovementModel>

        @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_ID_USER)
        fun getMovementByUserId(@Path("userId") userId: Long) : Call<GetMovementModel>

        @POST(Constants.HTTP.URL.URL_CREATE_MOVEMENT)
        fun createMovement(@Body movement: CreateMovementModel):Call<CreateMovementModel>

        @PUT(Constants.HTTP.URL.URL_UPDATE_MOVEMENT)
        fun updateMovementById(@Path("id") id: Long, @Body movement: GetMovementModel) :Call<GetMovementModel>

        @DELETE(Constants.HTTP.URL.URL_DELETE_MOVEMENT)
        fun deleteMovementById(@Path("id") id: Long) : Call<MovementResponse>


    }
