package com.mycompany.confinance.model.movement

import android.os.Parcelable
import com.mycompany.confinance.model.user.UserResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateMovementModel(
    var id: Long? = null,
    var type_movement: String,
    var value: Double,
    var description: String,
    var date: String,
    val user: UserResponse
) : Parcelable