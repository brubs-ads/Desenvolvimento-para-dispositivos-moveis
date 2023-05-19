package com.mycompany.confinance.model.error

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    val message: String? = null
) : Parcelable
