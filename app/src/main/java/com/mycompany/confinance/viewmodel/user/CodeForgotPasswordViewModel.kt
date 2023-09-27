package com.mycompany.confinance.viewmodel.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.confinance.repository.UserRepository
import com.mycompany.confinance.util.ResponseDialogCustom

class CodeForgotPasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val remote = UserRepository(context = application)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading :LiveData<Boolean> = _isLoading
    private val _error = MutableLiveData<ResponseDialogCustom>()
    val error :LiveData<ResponseDialogCustom> = _error
    fun reviewCode(codeOne: String?, codeTwo: String?, codeTree: String?, codeFour: String?) {
        val code = listOf(
            codeOne, codeTwo, codeTree, codeFour
        )

        if (!(code.any { it.isNullOrEmpty() })) {
            _isLoading.value = true
            _error.value = ResponseDialogCustom("dfdf",56)
        } else {
            _isLoading.value = false
            _error.value = ResponseDialogCustom("err",600)
        }


    }

}