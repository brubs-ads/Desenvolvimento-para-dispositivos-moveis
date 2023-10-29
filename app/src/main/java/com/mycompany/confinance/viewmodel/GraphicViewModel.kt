package com.mycompany.confinance.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.model.QueryResponse
import com.mycompany.confinance.model.ResponseGraphic
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.request.ApiListener
import com.mycompany.confinance.util.ResponseDialogCustom

class GraphicViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)
    private val _totalMovement = MutableLiveData<ResponseGraphic>()
    val totalMovement: LiveData<ResponseGraphic> = _totalMovement
    private val _erro = MutableLiveData<ResponseDialogCustom>()
    val erro: LiveData<ResponseDialogCustom> = _erro
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun queryMonthAndYear(month: Int, year: Int) {
        _isLoading.value = false
        repository.queryMonthAndYear(month = month, year = year,
            listener = object : ApiListener<QueryResponse> {
                override fun onSuccess(result: QueryResponse) {
                    val revenuePercentage = result.totalRevenues / result.total
                    val expensePercentage = result.totalExpenses / result.total

                    _totalMovement.value = ResponseGraphic(
                        percentageRevenue = revenuePercentage.toString(),
                        percentageExpense = expensePercentage.toString(),
                        totalRevenues = result.totalRevenues,
                        totalExpenses = result.totalExpenses
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