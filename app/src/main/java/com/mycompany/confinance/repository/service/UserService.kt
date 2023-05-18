package com.mycompany.confinance.repository.service

import com.mycompany.confinance.model.CreateUserModel
import com.mycompany.confinance.model.UserLoginModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {

    @POST(Constants.HTTP.URL.URL_LOGIN)
    fun login(
        @Body user : Map<String,String>
    ): Call<UserLoginModel>

    @POST(Constants.HTTP.URL.URL_CREATE_USER)
    fun create(
        @Body user: CreateUserModel
    ):Call<CreateUserModel>

}
