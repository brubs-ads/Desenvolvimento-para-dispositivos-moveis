package com.mycompany.confinance.repository.service

import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.model.movement.MovementResponse
import com.mycompany.confinance.model.movement.MovementTotalsModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface MovementService{

        @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_ID)
        fun getMovementById(@Path("id") id: Long) : Call<GetMovementModel>

        @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_ID_USER)
        fun getMovementByUserId(@Path("id") id: Long) : Call<List<GetMovementModel>>

        @GET(Constants.HTTP.URL.URL_RETURN_MOVEMENT_TOTALS)
        fun getMovementTotals(@Path("userId") userId: Long): Call<MovementTotalsModel>

        @POST(Constants.HTTP.URL.URL_CREATE_MOVEMENT)
        fun createMovement(@Body movement: CreateMovementModel):Call<CreateMovementModel>

        @PUT(Constants.HTTP.URL.URL_UPDATE_MOVEMENT)
        fun updateMovementById(@Path("id") id: Long, @Body movement: CreateMovementModel) :Call<CreateMovementModel>

        @DELETE(Constants.HTTP.URL.URL_DELETE_MOVEMENT)
        fun deleteMovementById(@Path("id") id: Long) : Call<MovementResponse>


    }
