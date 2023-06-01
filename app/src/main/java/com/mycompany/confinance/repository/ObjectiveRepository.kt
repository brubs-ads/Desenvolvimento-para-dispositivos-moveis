package com.mycompany.confinance.repository

import com.google.gson.Gson
import com.mycompany.confinance.model.objective.ObjectiveModel
import com.mycompany.confinance.model.objective.ObjectiveResponse
import com.mycompany.confinance.model.user.UserTeste
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.repository.service.ObjectiveService
import com.mycompany.confinance.util.Session
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class ObjectiveRepository {

    private val remote = RetrofitClient.getService(ObjectiveService::class.java)


    fun createObjective(value:Double,name:String,data: String, listener: ApiListener<ObjectiveModel>){
        val call= remote.createObjective(
            ObjectiveModel(null, value = value, name = name, date = data, user =
        UserTeste(Session.userId))
        )

        call.enqueue(object : Callback<ObjectiveModel> {
            override fun onResponse(call: Call<ObjectiveModel>, response: Response<ObjectiveModel>) {
                if(response.code() == HttpURLConnection.HTTP_CREATED){
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                }else{
                    val error = Gson().fromJson(response.errorBody()?.string(), ObjectiveResponse::class.java)
                    listener.onFailure(error.message + "Code: ${error.status}")
                }
            }

            override fun onFailure(call: Call<ObjectiveModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR.")
            }

        })
    }


}