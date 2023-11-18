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
import java.net.HttpURLConnection.*

class UserProfileViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository(application)
    private var _resultDeleteUser = MutableLiveData<Boolean>()
    val resultDeleteUser: LiveData<Boolean> = _resultDeleteUser
    private var _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> = _user
    private var _isLoadingUpdate = MutableLiveData<Boolean?>()
    val isLoadingUpdate: LiveData<Boolean?> = _isLoadingUpdate

    fun deleteUser() {
        repository.deleteUser(listener = object : ApiListener<ResponseModel> {
            override fun onSuccess(result: ResponseModel) {
                _resultDeleteUser.value = result.status == HTTP_OK
            }

            override fun onFailure(message: String?, code: Int) {
                _resultDeleteUser.value = false
            }

        })

    }

    fun getUser() {
        repository.getUser(listener = object : ApiListener<UserModel> {
            override fun onSuccess(result: UserModel) {
                _user.value = UserModel(id = result.id, name = result.name, email = result.email, password = "")
            }

            override fun onFailure(message: String?, code: Int) {

            }

        })
    }

    fun updateNameAndEmail(email: String, name: String, user: UserModel) {
        if (name != user.name) {
            repository.uptadeForNameAndEmail(name, null, listener = object : ApiListener<ResponseModel> {
                override fun onSuccess(result: ResponseModel) {
                    _isLoadingUpdate.value = result.status == HTTP_OK
                }

                override fun onFailure(message: String?, code: Int) {
                    _isLoadingUpdate.value = false
                }

            })
            } else if (email != user.email) {
            repository.uptadeForNameAndEmail(null, email, listener = object : ApiListener<ResponseModel> {
                override fun onSuccess(result: ResponseModel) {
                    _isLoadingUpdate.value = result.status == HTTP_OK
                }

                override fun onFailure(message: String?, code: Int) {
                    _isLoadingUpdate.value = false
                }

            })
            }else if (email != user.email && name != user.name){
            repository.uptadeForNameAndEmail(name, email, listener = object : ApiListener<ResponseModel> {
                override fun onSuccess(result: ResponseModel) {
                    _isLoadingUpdate.value = result.status == HTTP_OK
                }

                override fun onFailure(message: String?, code: Int) {
                    _isLoadingUpdate.value = false
                }

            })
        }
    }

    fun uptadePassword(password: String, newPassword: String, newPasswordAgain: String) {
        if (password != "" && newPassword != "" && newPasswordAgain != "") {
            if (newPassword.contentEquals(newPasswordAgain)) {
                repository.upgradePassword(
                    password = password,
                    newPassword = newPassword,
                    listener = object : ApiListener<ResponseModel> {
                        override fun onSuccess(result: ResponseModel) {
                            _isLoadingUpdate.value = result.status == HTTP_OK
                        }

                        override fun onFailure(message: String?, code: Int) {
                            if (code == HTTP_UNAUTHORIZED){
                                _isLoadingUpdate.value = null
                            }else{
                                _isLoadingUpdate.value = false
                            }

                        }

                    })
            }
        }

    }


}