package com.mycompany.confinance.repository

import android.content.Context
import com.google.gson.Gson
import com.mycompany.confinance.R
import com.mycompany.confinance.model.ObjectiveModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.model.User
import com.mycompany.confinance.request.ApiListener
import com.mycompany.confinance.request.Retrofit
import com.mycompany.confinance.service.ObjectiveService
import com.mycompany.confinance.util.SharedPreferencesUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_CREATED
import java.net.HttpURLConnection.HTTP_OK

class ObjectiveRepository(private val context: Context) {

    private val remote = Retrofit.getService(ObjectiveService::class.java)

    fun createObjective(
        value: Double,
        savedValue: Double,
        description: String,
        photo: Int,
        date: String,
        listener: ApiListener<ResponseModel>
    ) {
        val call =
            remote.create(
                ObjectiveModel(
                    null,
                    value = value,
                    savedValue = savedValue,
                    name = description,
                    photo = photo,
                    date = date,
                    user = User(SharedPreferencesUtil.getUserId(context = context))
                )
            )

        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == HTTP_CREATED) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
                    listener.onFailure(message = error.message, code = error.status)
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection), 500)
                } else {
                    listener.onFailure(context.getString(R.string.error_generic), 500)
                }
            }

        })
    }

    fun getObjectiveByIdUser(listener: ApiListener<List<ObjectiveModel>>) {
        val call = remote.getObjectiveByIdUser(SharedPreferencesUtil.getUserId(context))

        call.enqueue(object : Callback<List<ObjectiveModel>> {
            override fun onResponse(call: Call<List<ObjectiveModel>>, response: Response<List<ObjectiveModel>>) {
                if (response.code() == HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                }else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
                    listener.onFailure(message = error.message, code = error.status)
                }
            }

            override fun onFailure(call: Call<List<ObjectiveModel>>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection), 500)
                } else {
                    listener.onFailure(context.getString(R.string.error_generic), 500)
                }
            }

        })
    }

    fun deleteObjective(id: Long, listener: ApiListener<ResponseModel>) {
        val call = remote.deleteObjective(id = id)
        call.enqueue(object :Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == HTTP_OK){
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                }else{
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
                    listener.onFailure(message = error.message, code = error.status)
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection), 500)
                } else {
                    listener.onFailure(context.getString(R.string.error_generic), 500)
                }
            }

        })
    }

    fun updateObjective(id: Long,model: ObjectiveModel ,listener: ApiListener<ResponseModel>) {
        val call = remote.uptadeObjective(id = id, objective = model)

        call.enqueue(object :Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == HTTP_OK){
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                }else {
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
                    listener.onFailure(message = error.message, code = error.status)
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection), 500)
                } else {
                    listener.onFailure(context.getString(R.string.error_generic), 500)
                }
            }

        })

    }

}