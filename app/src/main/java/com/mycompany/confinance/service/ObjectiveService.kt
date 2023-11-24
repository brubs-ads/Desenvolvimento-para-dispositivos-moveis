package com.mycompany.confinance.service

import com.mycompany.confinance.model.ObjectiveModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ObjectiveService {

    @POST(Constants.HTTP.URL.URL_CREATE_OBJECTIVE)
    fun create(@Body objective: ObjectiveModel): Call<ResponseModel>

    @GET(Constants.HTTP.URL.URL_RETURN_OBJECTIVE_ID_USER)
    fun getObjectiveByIdUser(@Path("id") id: Long): Call<List<ObjectiveModel>>

    @DELETE(Constants.HTTP.URL.URL_DELETE_OBJECTIVE)
    fun deleteObjective(@Path("id") id: Long): Call<ResponseModel>

}