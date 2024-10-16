package com.felipeschoffen.onbudget.core.error

enum class LoginError : Error {
    INVALID_USER,
    INVALID_CREDENTIALS,
    TOO_MANY_REQUESTS,
    DISABLED_USER,
    SERVER_NETWORK_CONNECTION,
    UNKNOWN
}