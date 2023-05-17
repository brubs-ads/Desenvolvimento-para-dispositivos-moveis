package com.mycompany.confinance.repository

import com.mycompany.confinance.model.UserLoginModel
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.repository.service.UserService
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {

    private val remote = RetrofitClient.getService(UserService::class.java)

    fun login(email: String, password: String, listener: ApiListener<UserLoginModel>) {
        val user = hashMapOf("email" to email, "password" to password)
        var call = remote.login(user)
        call.enqueue(object : Callback<UserLoginModel> {
            override fun onResponse(
                call: Call<UserLoginModel>, response: Response<UserLoginModel>
            ) {
                if (response.code() == Constants.HTTP.CODE.CODE_SUCCESS) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                }
                else{
                    response.body()?.let {
                        listener.onFailure(it.message)
                    }
                }
            }

            override fun onFailure(call: Call<UserLoginModel>, t: Throwable) {
                listener.onFailure("ERROR, Para mais informações entre em contato com os Desenvolvedores")
            }
        })
    }
}