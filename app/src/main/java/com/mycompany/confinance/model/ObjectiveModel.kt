package com.mycompany.confinance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ObjectiveModel(
    var id: Long? = null,
    var value: Double?,
    var savedValue: Double?,
    var name: String?,
    var photo: Int?,
    var date: String?= null,
    val user: User? = null
) : Parcelable
