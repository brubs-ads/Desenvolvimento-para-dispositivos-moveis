package com.mycompany.confinance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QueryResponse(
    val total: Double,
    val totalExpenses: Double,
    val totalRevenues: Double,
    val userId: Int
) : Parcelable