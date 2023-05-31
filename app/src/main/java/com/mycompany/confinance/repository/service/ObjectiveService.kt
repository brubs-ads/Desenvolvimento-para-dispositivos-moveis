package com.mycompany.confinance.repository.service

import android.telecom.Call
import com.mycompany.confinance.model.objective.GetObjectiveModel
import com.mycompany.confinance.model.objective.ObjectiveResponse
import com.mycompany.confinance.util.Constants
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface ObjectiveService {

    @PUT(Constants.HTTP.URL.URL_UPDATE_OBJECTIVE)
    fun updateObjectiveById(@Path("id") id: Long, @Body objective: GetObjectiveModel) :Call

    @DELETE(Constants.HTTP.URL.URL_DELETE_OBJECTIVE)
    fun deleteObjectiveById(@Path("id") id: Long) : Call
}