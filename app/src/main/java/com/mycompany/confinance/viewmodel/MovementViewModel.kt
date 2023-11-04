package com.mycompany.confinance.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.repository.MovementRepository
import com.mycompany.confinance.request.ApiListener
import java.net.HttpURLConnection

class MovementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovementRepository(application)
    private var _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> = _isLoading
    private var _list = MutableLiveData<List<MovementModel>>()
    val list: LiveData<List<MovementModel>> = _list
    private var _isLoadingDelete = MutableLiveData<Boolean>()
    val isLoadingDelete: LiveData<Boolean> = _isLoadingDelete
    private var _isLoadingGetMovement = MutableLiveData<Boolean>()
    val isLoadingGetMovement: LiveData<Boolean> = _isLoadingGetMovement
    fun getMovement(typeMovement: String) {
        repository.getMovement(typeMovement = typeMovement, listener = object : ApiListener<List<MovementModel>> {
            override fun onSuccess(result: List<MovementModel>) {
                if (result.isNotEmpty()) {
                    _isLoading.value = false
                    _list.value = result
                }else{
                    _isLoading.value = null
                }
            }

            override fun onFailure(message: String, code: Int) {
                _isLoading.value = true
            }

        })
    }

    fun deleteMovement(id:Long) {
        repository.deleteMovement( idMovement = id, listener = object :ApiListener<ResponseModel>{
            override fun onSuccess(result: ResponseModel) {
                if (result.status == HttpURLConnection.HTTP_OK){
                    _isLoadingDelete.value = true
                }else{
                    _isLoadingDelete.value = false
                }
            }

            override fun onFailure(message: String, code: Int) {
                _isLoadingDelete.value = false
            }

        })
    }

    fun getMovementId(id: Long) {
        repository.getMovementById(idMovement = id, object :ApiListener<MovementModel>{
            override fun onSuccess(result: MovementModel) {
                if (result.id != null){
                    _isLoadingGetMovement.value = true
                }else{
                    _isLoadingGetMovement.value = false
                }
            }

            override fun onFailure(message: String, code: Int) {
                _isLoadingGetMovement.value = false
            }

        })
    }

}
