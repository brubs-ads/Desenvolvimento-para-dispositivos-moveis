package com.mycompany.confinance.viewmodel.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.R
import com.mycompany.confinance.model.ResponseModel
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.request.ApiListener
import com.mycompany.confinance.util.ResponseDialogCustom
import java.net.HttpURLConnection.HTTP_CREATED
import java.net.HttpURLConnection.HTTP_OK

class LoginViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private var _error = MutableLiveData<ResponseDialogCustom>()
    val error: LiveData<ResponseDialogCustom> = _error

    fun login(email: String?, password: String?) {
        if (email!!.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) && password!!.length >= 6) {
            email.lowercase()
            repository.login(
                email = email,
                password = password,
                listener = object : ApiListener<ResponseModel> {
                    override fun onSuccess(result: ResponseModel) {
                        if (result.status == HTTP_OK) {
                            _isLoading.value = true

                        } else {
                            _isLoading.value = false
                            _error.value = ResponseDialogCustom(result.message, result.status)
                        }
                    }

                    override fun onFailure(message: String?, code: Int) {
                        _isLoading.value = false
                        _error.value = ResponseDialogCustom(
                            message!!
                            ,code
                        )
                    }

                })

        } else {
            _isLoading.value = false
            _error.value = ResponseDialogCustom(
                application.getString(R.string.error_parameters_login),
                600
            )
        }
    }
}