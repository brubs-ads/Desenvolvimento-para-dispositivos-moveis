package com.mycompany.confinance.model

data class ObjectiveUpdate(val value: Double?,
                           val savedValue : Double?,
                           val name: String?,
                           val photo: Int = 0 ,
                           val date: String?
)
