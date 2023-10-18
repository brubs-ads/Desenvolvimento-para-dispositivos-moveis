package com.mycompany.confinance.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mycompany.confinance.repository.RevenueRepository

class CreateRevenueViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RevenueRepository()

    fun createRevenue(
        value: Long,
        description: String,
        data: String,
        fixedIncome: Boolean?,
        repetitions: Int?,
        category: String
    ){
        if (value.toDouble() != 0.00 && description !=  "" && data != null){
        }
    }
}