package com.mycompany.confinance.repository

import android.content.Context
import com.google.gson.Gson
import com.mycompany.confinance.R
import com.mycompany.confinance.model.LoginModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.request.ApiListener
import com.mycompany.confinance.request.Retrofit
import com.mycompany.confinance.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection.HTTP_OK

class UserRepository(private val context: Context) {

    private val remote = Retrofit.getService(UserService::class.java)
    fun login(email: String, password: String, listener: ApiListener<ResponseModel>) {
        val call = remote.login(LoginModel(email, password))

        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection))
                } else {
                    listener.onFailure(context.getString(R.string.error_generic))
                }
            }

        })

    }


}