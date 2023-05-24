package com.mycompany.confinance.repository

import com.google.gson.Gson
import com.mycompany.confinance.model.user.CreateUserModel
import com.mycompany.confinance.model.user.GetUserModel
import com.mycompany.confinance.model.user.ResponseUserModel
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.repository.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


class UserRepository {

    private val remote = RetrofitClient.getService(UserService::class.java)

    fun login(email: String, password: String, listener: ApiListener<ResponseUserModel>) {
        val loginData = hashMapOf("email" to email, "password" to password)
        val call = remote.login(loginData)
        call.enqueue(object : Callback<ResponseUserModel> {
            override fun onResponse(
                call: Call<ResponseUserModel>, response: Response<ResponseUserModel>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    response.let {
                        val error = Gson().fromJson(response.errorBody()?.string(), ResponseUserModel::class.java)
                        listener.onFailure(error.message + " Code: ${error.status} ")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUserModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }

    fun createAccount(
        name: String,
        email: String,
        password: String,
        listener: ApiListener<CreateUserModel>
    ) {
        val user = CreateUserModel(0, name, email, password)
        val call = remote.create(user)
        call.enqueue(object : Callback<CreateUserModel> {
            override fun onResponse(
                call: Call<CreateUserModel>, response: Response<CreateUserModel>
            ) {
                if (response.code() == HttpURLConnection.HTTP_CREATED) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(response.errorBody()?.string(), ResponseUserModel::class.java)
                    listener.onFailure(error.message + " Code: ${error.status} ")
                }
            }

            override fun onFailure(call: Call<CreateUserModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }

        })
    }

    fun getUserById(id: Long, listener: ApiListener<GetUserModel>) {
        val user = remote.getUserById(id)
        user.enqueue(object : Callback<GetUserModel> {
            override fun onResponse(call: Call<GetUserModel>, response: Response<GetUserModel>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(response.errorBody()?.string(), ResponseUserModel::class.java)
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<GetUserModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }
}
