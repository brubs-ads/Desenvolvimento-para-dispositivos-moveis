package com.mycompany.confinance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLoginModel(
    val message: String,
    val status: Int,
    var userId: Long? = null
) : Parcelable