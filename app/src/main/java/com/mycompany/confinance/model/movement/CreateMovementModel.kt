package com.mycompany.confinance.model.movement

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class CreateMovementModel(
    var id: Long? = null,
    var type_movement: String,
    var value: Double,
    var description: String,
    var date: Date,
    var userId: Long? = null
) : Parcelable