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
import java.net.HttpURLConnection

class ForgotPasswordViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(context = application)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _error = MutableLiveData<ResponseDialogCustom>()
    val error: LiveData<ResponseDialogCustom> = _error

    fun forgotPassword(email: String?) {
        if (email!!.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))) {
            email.lowercase()
            repository.forgotPassword(
                email = email,
                listener = object : ApiListener<ResponseModel> {
                    override fun onSuccess(result: ResponseModel) {
                        if (result.status == HttpURLConnection.HTTP_OK) {
                            _isLoading.value = true
                        } else {
                            _isLoading.value = false
                            _error.value = ResponseDialogCustom(result.message, result.status)
                        }

                    }

                    override fun onFailure(message: String, code: Int) {
                        _isLoading.value = false
                        _error.value = ResponseDialogCustom(message, code)
                    }
                })

        } else {
            _isLoading.value = false
            _error.value = ResponseDialogCustom(application.getString(R.string.It_looks_like_you_entered_an_incorrect_email), 600)
        }
    }
}