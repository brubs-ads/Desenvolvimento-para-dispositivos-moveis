package com.mycompany.confinance.repository.service

import com.mycompany.confinance.model.CreateUserModel
import com.mycompany.confinance.model.UserLoginModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {

    @POST(Constants.REDIRECTION.URL.URL_LOGIN)
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserLoginModel>

    @POST(Constants.REDIRECTION.URL.URL_CREATE_USER)
    @FormUrlEncoded
    fun create(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<CreateUserModel>

}
