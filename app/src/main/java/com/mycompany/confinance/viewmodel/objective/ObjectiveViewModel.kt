package com.mycompany.confinance.viewmodel.objective

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.model.ObjectiveModel
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.repository.ObjectiveRepository
import com.mycompany.confinance.request.ApiListener
import java.net.HttpURLConnection

class ObjectiveViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ObjectiveRepository(application)
    private var _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> = _isLoading
    private var _list = MutableLiveData<List<ObjectiveModel>>()
    val list: LiveData<List<ObjectiveModel>> = _list
    private var _isLoadingDelete = MutableLiveData<Boolean>()
    val isLoadingDelete: LiveData<Boolean> = _isLoadingDelete
    fun getObjective() {
        repository.getObjectiveByIdUser(object :ApiListener<List<ObjectiveModel>>{
            override fun onSuccess(result: List<ObjectiveModel>) {
                _isLoading.value = true
                if (result.isNotEmpty()){
                    _isLoading.value = false
                    _list.value = result
                }else{
                    _isLoading.value = null
                }
            }

            override fun onFailure(message: String?, code: Int) {
                if (code == 404){
                    _isLoading.value = null
                }else{
                    _isLoading.value = true
                }
            }

        })
    }

    fun deleteObjective(id: Long) {
        repository.deleteObjective(id = id, object :ApiListener<ResponseModel>{
            override fun onSuccess(result: ResponseModel) {
                if (result.status == HttpURLConnection.HTTP_OK){
                    _isLoadingDelete.value = true
                }else{
                    _isLoadingDelete.value = false
                }
            }

            override fun onFailure(message: String?, code: Int) {
                _isLoadingDelete.value = false
            }
        })

    }
}