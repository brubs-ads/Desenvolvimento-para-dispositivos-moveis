package com.mycompany.confinance.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.model.QueryResponse
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.request.ApiListener
import com.mycompany.confinance.util.ResponseDialogCustom

class HomeViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository(application)
    private val _total = MutableLiveData<Double>()
    val total: LiveData<Double> = _total
    private val _totalRevenue = MutableLiveData<QueryResponse>()
    val totalRevenue: LiveData<QueryResponse> = _totalRevenue
    private val _erro = MutableLiveData<ResponseDialogCustom>()
    val erro: LiveData<ResponseDialogCustom> = _erro
    fun queryMonthAndYear(month: Int, year: Int) {
        repository.queryMonthAndYear(month = month, year = year,
            listener = object : ApiListener<QueryResponse> {
                override fun onSuccess(result: QueryResponse) {
                    _total.value = result.total
                    _totalRevenue.value = QueryResponse(
                        0.0, result.totalExpenses, result.totalRevenues,
                        result.userId
                    )
                }

                override fun onFailure(message: String, code: Int) {
                    _erro.value = ResponseDialogCustom(message, code)
                }

            })
    }


}