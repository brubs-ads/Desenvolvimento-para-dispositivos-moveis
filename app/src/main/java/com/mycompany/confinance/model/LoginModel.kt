package com.mycompany.confinance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel(
    val email: String? = null,
    val password: String? = null
) : Parcelable
