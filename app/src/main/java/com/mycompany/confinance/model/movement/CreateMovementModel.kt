package com.mycompany.confinance.model.movement

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
@Parcelize
data class CreateMovementModel(
    var id: Long? = null,
    var type_movement: String,
    var value: Double,
    var description: String,
    var date: LocalDate,
    var userId: Long? = null
) : Parcelable