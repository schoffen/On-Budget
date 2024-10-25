package com.felipeschoffen.onbudget.core.error

import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.domain.util.ResourceProvider

enum class LoginError : Error {
    INVALID_USER,
    INVALID_CREDENTIALS,
    TOO_MANY_REQUESTS,
    DISABLED_USER,
    SERVER_NETWORK_CONNECTION,
    UNKNOWN
}

fun LoginError.toString(resourceProvider: ResourceProvider): String {
    val resId = when(this) {
        LoginError.INVALID_USER -> R.string.error_invalid_user
        LoginError.INVALID_CREDENTIALS -> R.string.error_invalid_credentials
        LoginError.TOO_MANY_REQUESTS -> R.string.error_many_requests
        LoginError.DISABLED_USER -> R.string.error_disabled_user
        LoginError.SERVER_NETWORK_CONNECTION -> R.string.error_database_network_connection
        LoginError.UNKNOWN -> R.string.error_unknown
    }

    return resourceProvider.getString(resId)
}