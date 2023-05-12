package com.mycompany.confinance.repository

import com.mycompany.confinance.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    companion object {
        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            val http = OkHttpClient.Builder().build()
            if (!::INSTANCE.isInitialized) {
                synchronized(RetrofitClient::class) {
                    INSTANCE =
                        Retrofit.Builder().baseUrl(Constants.REDIRECTION.URL.URL_BASE).client(http)
                            .addConverterFactory(GsonConverterFactory.create()).build()
                }
            }
            return INSTANCE
        }

        fun <T> getService(service: Class<T>): T {
            return getRetrofitInstance().create(service)
        }

    }

}