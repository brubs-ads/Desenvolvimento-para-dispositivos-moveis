package com.mycompany.confinance.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.repository.MovementRepository
import com.mycompany.confinance.request.ApiListener
import java.net.HttpURLConnection

class CreateRevenueViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = MovementRepository(application)
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading :LiveData<Boolean> = _isLoading

    fun createRevenue(
        value: Long,
        description: String,
        data: String?,
        fixedIncome: Boolean?,
        repetitions: String?,
        photo: Int
    ){
        if (value != 0.0.toLong() && description != "" && data != null) {
            if (fixedIncome == false) {
                repository.createMovement(
                    context = application,
                    codeType = 1,
                    value = value,
                    description = description,
                    fixedIncome = null,
                    data = data,
                    repetitions = repetitions,
                    photo = photo,
                    listener = object : ApiListener<ResponseModel> {
                        override fun onSuccess(result: ResponseModel) {
                            if (result.status == HttpURLConnection.HTTP_CREATED) {
                                _isLoading.value = true
                            }else{
                                _isLoading.value = false
                            }
                        }

                        override fun onFailure(message: String?, code: Int) {
                            _isLoading.value = false
                        }

                    }
                )
            } else {
                repository.createMovement(
                    context = application,
                    codeType = 1,
                    value = value,
                    description = description,
                    data = data,
                    fixedIncome = fixedIncome,
                    repetitions = null,
                    photo = photo,
                    listener = object : ApiListener<ResponseModel> {
                        override fun onSuccess(result: ResponseModel) {
                            if (result.status == HttpURLConnection.HTTP_CREATED) {
                                _isLoading.value = true
                            }else{
                                _isLoading.value = false
                            }
                        }

                        override fun onFailure(message: String?, code: Int) {
                            _isLoading.value = false
                        }

                    }
                )
            }
        }
    }
}