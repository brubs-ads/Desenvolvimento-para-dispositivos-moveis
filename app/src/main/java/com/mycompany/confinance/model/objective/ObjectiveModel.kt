package com.mycompany.confinance.model.objective

import android.os.Parcelable
import com.mycompany.confinance.model.user.UserTeste
import kotlinx.parcelize.Parcelize

@Parcelize
data class ObjectiveModel (
    var id: Long? = null,
    var value: Double,
    var name: String,
    var date: String,
    var user: UserTeste

    ): Parcelable