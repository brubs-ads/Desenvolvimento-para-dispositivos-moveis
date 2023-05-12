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
        object URL{
            const val URL_BASE: String = "localhost:8080/"
            const val URL_LOGIN: String = "user/login"
            const val URL_CREATE_USER: String = "user"
        }
    }
}