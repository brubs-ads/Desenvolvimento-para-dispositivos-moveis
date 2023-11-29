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

class NewPasswordViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository(context = application)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _error = MutableLiveData<ResponseDialogCustom>()
    val error: LiveData<ResponseDialogCustom> = _error
    fun newPassword(email: String?, password: String, passwordConfirm: String) {
        if (password.contentEquals(passwordConfirm)) {
            repository.resetPassword(
                email = email!!,
                password = password,
                listener = object : ApiListener<ResponseModel> {
                    override fun onSuccess(result: ResponseModel) {
                        if (result.status == 200) {
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
            _error.value = ResponseDialogCustom(application.getString(R.string.password_does_not_work), 600)
        }
    }
}