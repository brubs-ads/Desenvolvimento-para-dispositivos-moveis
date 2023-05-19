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
            const val URL_BASE: String = "http://192.168.13.104:8080/"
            const val URL_LOGIN: String = "user/login"
            const val URL_CREATE_USER: String = "user"
        }

        object CODE {
            const val SUCCESS: Int = 200
            const val CREATE: Int = 201
        }
    }
}