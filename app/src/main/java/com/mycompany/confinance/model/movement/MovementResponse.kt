package com.mycompany.confinance.model.movement

import android.os.Message
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovementResponse (
    val message: String,
    val status: Int,
    val UserId: Long? = null
) : Parcelable