package com.mycompany.confinance.repository.service

import com.mycompany.confinance.model.user.UserModel
import com.mycompany.confinance.model.user.LoginUser
import com.mycompany.confinance.model.user.ResponseUserModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST(Constants.HTTP.URL.URL_LOGIN)
    fun login(
        @Body user:LoginUser
    ): Call<ResponseUserModel>

    @POST(Constants.HTTP.URL.URL_CREATE_USER)
    fun create(
        @Body user: UserModel
    ): Call<UserModel>

    @GET(Constants.HTTP.URL.URL_RETURN_USER)
    fun getUserById(
        @Path("id") id: Long
    ): Call<UserModel>

    @DELETE(Constants.HTTP.URL.URL_DELETE_USER)
    fun deleteUser(
        @Path("id") id: Long
    ):Call<ResponseUserModel>
}
