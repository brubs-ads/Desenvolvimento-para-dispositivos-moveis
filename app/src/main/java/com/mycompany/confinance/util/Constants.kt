package com.mycompany.confinance.util


class Constants {
    companion object REDIRECTION {
        object KEY {
            const val ACCOUNT: String = "Activity_Account"
            const val LOGIN: String = "Activity_Login"
            const val MENU: String = "Activity_Menu"
        }

        object VALUE {
            const val ACCOUNT: Boolean = true
            const val LOGIN: Boolean = true
            const val MENU: Boolean = true
        }
    }

    object HTTP {
        object URL {
            const val URL_SELTON: String = "http://192.168.13.104:8080/"
            const val URL_JOAO: String = "http://172.20.10.2:8080/"
            const val URL_THAMYRES: String = "http://192.168.1.15:8080/"
            const val URL_LOGIN: String = "user/login"
            const val URL_CREATE_USER: String = "user"
            const val URL_RETURN_USER:String = "user/{id}"
            const val URL_DELETE_USER:String ="user/{id}"
            const val URL_CREATE_MOVEMENT: String = "movement"
            const val URL_UPDATE_MOVEMENT: String = "movement/{id}"
            const val URL_RETURN_MOVEMENT_ID: String = "movement/{id}"
            const val URL_RETURN_MOVEMENT_ID_USER: String = "movement/user/{id}"
             const val URL_RETURN_MOVEMENT_LIST: String = "movement"
            const val URL_DELETE_MOVEMENT: String = "movement/{id}"
            const val URL_UPDATE_OBJECTIVE: String = "objective/{id}"
            const val URL_DELETE_OBJECTIVE: String = "objective/{id}"
        }
    }

    object MOVEMENT{
        const val REVENUE: String = "receita"
        const val EXPENSE: String = "despesa"
    }
}