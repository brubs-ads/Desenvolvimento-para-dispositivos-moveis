package com.mycompany.confinance.repository.listener

interface ApiListener <T> {
    fun onSuccess(result : T)
    fun onFailure(message: String)
}