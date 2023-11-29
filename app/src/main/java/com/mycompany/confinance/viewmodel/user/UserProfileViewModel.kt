package com.mycompany.confinance.viewmodel.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.model.UserModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.request.ApiListener
import java.net.HttpURLConnection

class UserProfileViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository(application)
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private var _result = MutableLiveData<UserModel>()
    val result :LiveData<UserModel> = _result


    fun deleteUser() {
        repository.deleteUser(listener = object : ApiListener<ResponseModel> {
            override fun onSuccess(result: ResponseModel) {
                _isLoading.value = result.status == HttpURLConnection.HTTP_OK
            }

            override fun onFailure(message: String, code: Int) {
                _isLoading.value = false
            }

        })

    }

    fun getUser() {
        repository.getUser(listener = object :ApiListener<UserModel>{
            override fun onSuccess(result: UserModel) {
                _result.value = UserModel(id = result.id, name = result.name, email = result.email, password = "")
            }

            override fun onFailure(message: String, code: Int) {


            }

        })
    }


}