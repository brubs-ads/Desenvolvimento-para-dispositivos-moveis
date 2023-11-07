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
    private val _totalBalance = MutableLiveData<Double>()
    val totalBalance: LiveData<Double> = _totalBalance
    private val _totalMovement = MutableLiveData<QueryResponse>()
    val totalMovement: LiveData<QueryResponse> = _totalMovement
    private val _erro = MutableLiveData<ResponseDialogCustom>()
    val erro: LiveData<ResponseDialogCustom> = _erro
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading :LiveData<Boolean> = _isLoading
    fun queryMonthAndYear(month: Int, year: Int) {
        _isLoading.value = false
        repository.queryMonthAndYear(month = month, year = year,
            listener = object : ApiListener<QueryResponse> {
                override fun onSuccess(result: QueryResponse) {
                    _totalBalance.value = result.total
                    _totalMovement.value = QueryResponse(
                        result.total, result.totalExpenses, result.totalRevenues,
                        result.userId
                    )
                    _isLoading.value = true
                }

                override fun onFailure(message: String, code: Int) {
                    _isLoading.value = false
                    _erro.value = ResponseDialogCustom(message, code)
                }

            })
    }


}