package com.mycompany.confinance.service

import com.mycompany.confinance.model.*
import com.mycompany.confinance.util.Constants
import retrofit2.Call
import retrofit2.http.*

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

    @POST(Constants.HTTP.URL.URL_EMAIL_VERIFICATION_CODE)
    fun verificationCode(@Body code: ReviewCoding): Call<ResponseModel>

    @POST(Constants.HTTP.URL.URL_RESET_PASSWORD)
    fun resetPassword(@Body resetPassword: ResetPasswordModel): Call<ResponseModel>


    @GET(Constants.HTTP.URL.QUERY_MONTH_AND_YEAR)
    fun queryMonthAndYear(
        @Path("userId") userId: Long,
        @Path("month") month: Int,
        @Path("year") year: Int
    ): Call<QueryResponse>

    @DELETE(Constants.HTTP.URL.URL_DELETE_USER)
    fun deleteUser(@Path("id") id: Long): Call<ResponseModel>

    @GET(Constants.HTTP.URL.URL_RETURN_USER)
    fun getUser(@Path("id") id: Long): Call<UserModel>

    @PATCH(Constants.HTTP.URL.URL_UPTADE_USER)
    fun uptadeUser(@Path("id") id: Long, @Body user: UserModel): Call<ResponseModel>

    @PUT(Constants.HTTP.URL.URL_RESET_PASSWORD_USER)
    fun uptadePassword(@Path("id") id: Long,@Body password: PasswordResetRequest):Call<ResponseModel>
}