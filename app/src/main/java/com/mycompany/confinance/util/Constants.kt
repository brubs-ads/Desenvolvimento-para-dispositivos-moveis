package com.mycompany.confinance.util


class Constants {
    object HTTP {
        object URL {
            const val URL_SELTON: String = "http://192.168.13.107:8080/"
            const val URL_LOGIN: String = "user/login"
            const val URL_CREATE_USER: String = "user/create"
            const val URL_RETURN_USER:String = "user/{id}"
            const val URL_DELETE_USER:String ="user/{id}"
            const val URL_CREATE_MOVEMENT: String = "movement"
            const val URL_UPDATE_MOVEMENT: String = "movement/{id}"
            const val URL_RETURN_MOVEMENT_ID: String = "movement/{id}"
            const val URL_RETURN_MOVEMENT_ID_USER: String = "movement/user/{id}"
            const val URL_RETURN_MOVEMENT_TOTALS : String = "movement/totals/user/{userId}"
            const val URL_DELETE_MOVEMENT: String = "movement/{id}"
            const val URL_CREATE_OBJECTIVE: String = "objective"
            const val URL_UPDATE_OBJECTIVE: String = "objective/{id}"
            const val URL_RETURN_OBJECTIVE_ID_USER: String = "objective/user/{id}"
            const val URL_DELETE_OBJECTIVE: String = "objective/{id}"
        }
    }
}