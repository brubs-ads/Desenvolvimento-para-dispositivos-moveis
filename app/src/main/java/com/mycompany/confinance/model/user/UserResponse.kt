package com.mycompany.confinance.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    val id: Long?= null
) : Parcelable