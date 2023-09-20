package com.mycompany.confinance.service

import com.mycompany.confinance.model.LoginModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST(Constants.HTTP.URL.URL_LOGIN)
    fun login(
        @Body user:LoginModel
    ): Call<ResponseModel>
}