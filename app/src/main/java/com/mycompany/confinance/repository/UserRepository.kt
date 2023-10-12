package com.mycompany.confinance.repository

import android.content.Context
import com.google.gson.Gson
import com.mycompany.confinance.R
import com.mycompany.confinance.model.*
import com.mycompany.confinance.request.ApiListener
import com.mycompany.confinance.request.Retrofit
import com.mycompany.confinance.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection.HTTP_CREATED
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_OK

class UserRepository(private val context: Context) {

    private val remote = Retrofit.getService(UserService::class.java)

    private fun saveState(isConnected: Boolean) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("http_connected", isConnected)
        editor.apply()
    }


    fun login(email: String, password: String, listener: ApiListener<ResponseModel>) {
        val call = remote.login(LoginModel(email, password))

        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                        saveState(true)
                    }
                } else {
                    val error = Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
                    listener.onFailure(context.getString(R.string.error_failure_login), error.status)
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection), 500)
                } else {
                    listener.onFailure(context.getString(R.string.error_generic), 500)
                }
            }

        })

    }

    fun createAccount(
        name: String, email: String, password: String, listener: ApiListener<ResponseModel>
    ) {
        val call = remote.createAccount(UserModel(null, name, email, password))

        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == HTTP_CREATED) {
                    response.body()?.let {
                        listener.onSuccess(it)
                        saveState(true)
                    }
                } else if (response.code() == HTTP_FORBIDDEN) {
                    listener.onFailure(context.getString(R.string.email_already_linked), response.code())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection), 500)
                } else {
                    listener.onFailure(context.getString(R.string.error_generic), 500)
                }
            }

        })
    }

    fun forgotPassword(email: String, listener: ApiListener<ResponseModel>) {
        val call = remote.emailSending(email = email)
        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
                    listener.onFailure(context.getString(R.string.email_Exist_Please_Check), code = error.status)
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection), 500)
                } else {
                    listener.onFailure(context.getString(R.string.error_generic), 500)
                }
            }

        })
    }

    fun verificationCode(
        email: String,
        codeOne: String,
        codeTwo: String,
        codeTree: String,
        codeFour: String,
        listener: ApiListener<ResponseModel>
    ) {
        val code = codeOne.plus(codeTwo).plus(codeTree).plus(codeFour)
        val call = remote.verificationCode(ReviewCoding(email = email, code = code))

        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
                    listener.onFailure(context.getString(R.string.wrong_verification_code), code = error.status)
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection), 500)
                } else {
                    listener.onFailure(context.getString(R.string.error_generic), 500)
                }
            }

        })
    }

    fun resetPassword(email: String, password: String, listener: ApiListener<ResponseModel>) {
        val call = remote.resetPassword(ResetPasswordModel(email = email, newPassword = password))

        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == HTTP_OK) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                } else {
                    val error = Gson().fromJson(response.errorBody()?.string(), ResponseModel::class.java)
                    listener.onFailure(message = error.message, code = error.status)
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                if (t is IOException) {
                    listener.onFailure(context.getString(R.string.error_no_connection), 500)
                } else {
                    listener.onFailure(context.getString(R.string.error_generic), 500)
                }
            }

        })
    }

}