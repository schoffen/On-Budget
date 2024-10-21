package com.felipeschoffen.onbudget.core.error

enum class DatabaseError : Error {
    NETWORK_CONNECTION,
    DOCUMENT_NOT_FOUND,
    USER_NOT_LOGGED_IN,
    UNKNOWN
}