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
//    private val repository = UserRepository(application)
//    private val _result = MutableLiveData<QueryResponse>()
//    val result: LiveData<QueryResponse> = _result
//    private val _erro = MutableLiveData<ResponseDialogCustom>()
//    val erro: LiveData<ResponseDialogCustom> = _erro
//    fun queryMonthAndYear(month: Int, yearatt: Int) {
//        repository.queryMonthAndYear(month = month, year = yearatt,
//            listener = object : ApiListener<QueryResponse> {
//                override fun onSuccess(result: QueryResponse) {
//                    _result.value = result
//                }
//
//                override fun onFailure(message: String, code: Int) {
//                    _erro.value = ResponseDialogCustom(message, code)
//                }
//
//            })
//    }


}