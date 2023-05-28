package com.mycompany.confinance.model.movement

import android.os.Parcelable
import com.mycompany.confinance.model.user.GetUserModel
import com.mycompany.confinance.model.user.UserTeste
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class CreateMovementModel(
    var id: Long? = null,
    var type_movement: String,
    var value: Double,
    var description: String,
    var date: String,
    val user: UserTeste
) : Parcelable