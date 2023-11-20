package com.mycompany.confinance.util


class Constants {

    object KEY{
       const val KEY_USER_ID: String = "id"
        const val KEY_EMAIL: String = "email"
    }

    object HTTP {
        object URL {
            const val URL_SELTON: String = "http://192.168.13.107:8080/"
            const val URL_LOGIN: String = "user/login"
            const val URL_CREATE_USER: String = "user/create"
            const val URL_RETURN_USER:String = "user/{id}"
            const val URL_DELETE_USER:String ="user/{id}"
            const val URL_UPTADE_USER:String = "user/{id}"
            const val URL_CREATE_MOVEMENT: String = "movement/"
            const val URL_UPDATE_MOVEMENT: String = "movement/{id}"
            const val URL_RETURN_MOVEMENT_ID: String = "movement/{id}"
            const val URL_RETURN_REVENUE_ID_USER_BY_PERIOD: String = "movement/user/{id}/revenues"
            const val URL_RETURN_EXPENSE_ID_USER_BY_PERIOD: String = "movement/user/{id}/expenses"
            const val URL_RETURN_MOVEMENT_TOTALS : String = "movement/totals/user/{userId}"
            const val URL_DELETE_MOVEMENT: String = "movement/{id}"
            const val URL_CREATE_OBJECTIVE: String = "objective"
            const val URL_UPDATE_OBJECTIVE: String = "objective/{id}"
            const val URL_RETURN_OBJECTIVE_ID_USER: String = "objective/user/{id}"
            const val URL_DELETE_OBJECTIVE: String = "objective/{id}"
            const val URL_EMAIL_SENDING: String = "user/send-mail/{email}"
            const val URL_EMAIL_VERIFICATION_CODE :String = "user/validate-code"
            const val URL_RESET_PASSWORD:String = "user/reset-password"
            const val URL_RESET_PASSWORD_USER: String ="user/{id}/reset-password"
            const val QUERY_MONTH_AND_YEAR: String = "movement/user/{userId}/month/{month}/year/{year}"
        }
    }
}