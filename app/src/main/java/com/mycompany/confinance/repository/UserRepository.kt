package com.mycompany.confinance.repository

import com.google.gson.Gson
import com.mycompany.confinance.model.user.UserModel
import com.mycompany.confinance.model.user.LoginUser
import com.mycompany.confinance.model.user.ResponseUserModel
import com.mycompany.confinance.repository.listener.ApiListener
import com.mycompany.confinance.repository.service.UserService
import com.mycompany.confinance.util.Session
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


class UserRepository {

    private val remote = RetrofitClient.getService(UserService::class.java)

    fun login(email: String, password: String, listener: ApiListener<ResponseUserModel>) {
        val user = LoginUser(email, password)
        val call = remote.login(user)
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
        listener: ApiListener<UserModel>
    ) {
        val user = UserModel(0, name, email, password)
        val call = remote.create(user)
        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(
                call: Call<UserModel>, response: Response<UserModel>
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

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }

        })
    }

    fun getUserById(id: Long, listener: ApiListener<UserModel>) {
        val user = remote.getUserById(id)
        user.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(response.errorBody()?.string(), ResponseUserModel::class.java)
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }
        })
    }

    fun deleteUser(id: Long, listener: ApiListener<ResponseUserModel>){
        val user = remote.deleteUser(id)
        user.enqueue(object : Callback<ResponseUserModel>{
            override fun onResponse(call: Call<ResponseUserModel>,response: Response<ResponseUserModel>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    response.body()?.let {
                            listener.onSuccess(it)
                    }
                }else{
                    val error = Gson().fromJson(response.errorBody()?.string(), ResponseUserModel::class.java)
                    listener.onFailure(error.message)
                }
            }

            override fun onFailure(call: Call<ResponseUserModel>, t: Throwable) {
                listener.onFailure("ERRO, ENTRA EM CONTATO COM O DESENVOLVEDOR")
            }

        })
    }
}
