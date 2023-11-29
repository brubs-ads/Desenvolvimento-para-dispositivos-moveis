package com.mycompany.confinance.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.repository.MovementRepository
import com.mycompany.confinance.request.ApiListener

class MovementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovementRepository(application)
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private var _list = MutableLiveData<List<MovementModel>>()
    val list: LiveData<List<MovementModel>> = _list

    fun getMovement(typeMovement: String) {
        _isLoading.value = false
        repository.getMovement(typeMovement = typeMovement, listener = object : ApiListener<List<MovementModel>> {
            override fun onSuccess(result: List<MovementModel>) {
                if (result.isNotEmpty()) {
                    _list.value = result
                    _isLoading.value = true
                } else {
                    _isLoading.value = false
                }
            }

            override fun onFailure(message: String, code: Int) {
                _isLoading.value = false
            }

        })
    }

}
