package com.mycompany.confinance.model

import java.time.LocalDate

data class MovementModel (
    var id: Long? = null,
    var type_movement: String,
    var value: Double,
    var description: String,
    var date: LocalDate,
    var userId: Long? = null

    )
