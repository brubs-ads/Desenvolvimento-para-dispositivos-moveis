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

class CreateAccountViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(context = application)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _error = MutableLiveData<ResponseDialogCustom>()
    val error: LiveData<ResponseDialogCustom> = _error

    fun createAccount(name: String?, email: String?, password: String?) {
        if (name!!.length >=3 && email!!.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            && password!!.length >= 6
        ) {
            repository.createAccount(
                name = name,
                email = email,
                password = password,
                listener = object : ApiListener<ResponseModel> {
                    override fun onSuccess(result: ResponseModel) {
                        if (result.status == HTTP_CREATED) {
                            _isLoading.value = true
                        }
                    }

                    override fun onFailure(message: String, code: Int) {
                        _isLoading.value = false
                        _error.value = ResponseDialogCustom(message, code)
                    }

                })

        } else {
            _isLoading.value = false
            _error.value = ResponseDialogCustom(
                application.getString(R.string.something_seems_to_be_missing),
                600
            )

        }
    }

}