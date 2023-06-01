package com.mycompany.confinance.repository.service

import com.mycompany.confinance.model.objective.ObjectiveModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ObjectiveService {

    @POST(Constants.HTTP.URL.URL_CREATE_OBJECTIVE)
    fun createObjective(@Body objective: ObjectiveModel):Call<ObjectiveModel>

    @PUT(Constants.HTTP.URL.URL_UPDATE_OBJECTIVE)
    fun updateObjectiveById(@Path("id") id: Long, @Body objective: ObjectiveModel) :
            Call<ObjectiveModel>

    @DELETE(Constants.HTTP.URL.URL_DELETE_OBJECTIVE)
    fun deleteObjectiveById(@Path("id") id: Long) : Call<ObjectiveModel>
}