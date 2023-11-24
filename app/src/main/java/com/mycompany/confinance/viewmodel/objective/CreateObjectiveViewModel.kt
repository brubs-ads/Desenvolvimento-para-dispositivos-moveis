package com.mycompany.confinance.viewmodel.objective

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.repository.ObjectiveRepository
import com.mycompany.confinance.request.ApiListener
import java.net.HttpURLConnection
import java.net.HttpURLConnection.*

class CreateObjectiveViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ObjectiveRepository(context = application)
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun createObjective(
        value: Double,
        savedValue: Double,
        description: String,
        photo: Int?,
        date: String
    ) {
        repository.createObjective(
            value = value,
            savedValue = savedValue,
            description = description,
            photo = photo!!,
            date = date,
            listener = object :ApiListener<ResponseModel>{
                override fun onSuccess(result: ResponseModel) {
                    if (result.status == HTTP_CREATED){
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