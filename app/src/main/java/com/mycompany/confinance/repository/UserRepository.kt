package com.mycompany.confinance.repository

import com.mycompany.confinance.model.user.CreateUserModel
import com.mycompany.confinance.model.user.GetUserModel
import com.mycompany.confinance.model.user.UserLoginModel
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.repository.service.UserService
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {

    private val remote = RetrofitClient.getService(UserService::class.java)

    fun login(email: String, password: String, listener: ApiListener<UserLoginModel>) {
        val loginData = hashMapOf("email" to email, "password" to password)
        val call = remote.login(loginData)
        call.enqueue(object : Callback<UserLoginModel> {
            override fun onResponse(
                call: Call<UserLoginModel>, response: Response<UserLoginModel>
            ) {
                if (response.code() == Constants.HTTP.CODE.SUCCESS) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    response.let {
                        listener.onFailure(it.message())
                    }
                }
            }

            override fun onFailure(call: Call<UserLoginModel>, t: Throwable) {
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
                if (response.code() == Constants.HTTP.CODE.CREATE) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    listener.onFailure(response.message())
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
                if (response.code() == Constants.HTTP.CODE.SUCCESS) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val s = ""
                }
            }

            override fun onFailure(call: Call<GetUserModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }
}
