package com.mycompany.confinance.service

import com.mycompany.confinance.model.LoginModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.model.UserModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST(Constants.HTTP.URL.URL_LOGIN)
    fun login(
        @Body user: LoginModel
    ): Call<ResponseModel>

    @POST(Constants.HTTP.URL.URL_CREATE_USER)
    fun createAccount(
        @Body user: UserModel
    ): Call<ResponseModel>

    @POST(Constants.HTTP.URL.URL_EMAIL_SENDING)
    fun emailSending(@Path("email") email: String): Call<ResponseModel>
}