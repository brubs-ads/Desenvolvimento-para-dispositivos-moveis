package com.mycompany.confinance.model.objective

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ObjectiveResponse (
    val message: String,
    val status: Int,
    var userId: Long? = null
        ) : Parcelable