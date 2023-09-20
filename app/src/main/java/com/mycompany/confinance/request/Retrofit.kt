package com.mycompany.confinance.request

import com.mycompany.confinance.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val instance: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.HTTP.URL.URL_SELTON).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getService(service: Class<T>): T {
        return instance.create(service)
    }
}