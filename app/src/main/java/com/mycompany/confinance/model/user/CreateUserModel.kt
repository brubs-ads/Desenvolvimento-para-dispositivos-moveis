package com.mycompany.confinance.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateUserModel(
    var id: Long? = null,
    val name:String,
    val email:String,
    val password:String
) : Parcelable