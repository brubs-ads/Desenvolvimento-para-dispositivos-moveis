package com.mycompany.confinance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovementModel(
    val id: Long? = null,
    val type_movement: String,
    val value: Long,
    val description: String,
    val date: String,
    val Photo: Int,
    val fixedIncome: Boolean?,
    val recurrenceFrequency: String?,
    val recurrenceIntervals: Int?,
    val user: User
) : Parcelable