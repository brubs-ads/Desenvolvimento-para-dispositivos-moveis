package com.mycompany.confinance.request

interface ApiListener<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String?, code: Int)
}