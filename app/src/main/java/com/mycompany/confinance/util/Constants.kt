package com.mycompany.confinance.util


class Constants {
    companion object REDIRECTION {
        object KEY {
            const val ACCOUNT: String = "Activity_Account"
            const val LOGIN: String = "Activity_Login"
        }

        object VALUE {
            const val ACCOUNT: Boolean = true
            const val LOGIN: Boolean = true
        }
    }

    object HTTP {
        object URL {
            const val URL_SELTON: String = "http://192.168.13.104:8080/"
            const val URL_JOAO: String = "http://192.168.43.58:8080/"
            const val URL_LOGIN: String = "user/login"
            const val URL_CREATE_USER: String = "user"
            const val URL_RETURN_USER:String = "user/{id}"
            const val URL_CREATE_MOVEMENT: String = "movement"
            const val URL_UPDATE_MOVEMENT: String = "movement/{id}"
            const val URL_RETURN_MOVEMENT_ID: String = "movement/{id}"
            const val URL_RETURN_MOVEMENT_ID_USER: String = "movement/user{id}"
             const val URL_RETURN_MOVEMENT_LIST: String = "movement"
            const val URL_DELETE_MOVEMENT: String = "movement/{id}"
        }

        object CODE {
            const val SUCCESS: Int = 200
            const val CREATE: Int = 201
        }
    }
}