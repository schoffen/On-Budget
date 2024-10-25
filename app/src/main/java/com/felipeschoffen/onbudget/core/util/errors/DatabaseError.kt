package com.felipeschoffen.onbudget.core.util.errors

import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.domain.util.ResourceProvider

enum class DatabaseError : Error {
    NETWORK_CONNECTION,
    DOCUMENT_NOT_FOUND,
    USER_NOT_LOGGED_IN,
    UNKNOWN
}

fun DatabaseError.toString(resourceProvider: ResourceProvider): String {
    val redId = when(this) {
        DatabaseError.NETWORK_CONNECTION -> R.string.error_database_network_connection
        DatabaseError.UNKNOWN -> R.string.error_unknown
        DatabaseError.DOCUMENT_NOT_FOUND -> R.string.error_database_document_not_found
        DatabaseError.USER_NOT_LOGGED_IN -> R.string.error_user_not_sign_in
    }

    return resourceProvider.getString(redId)
}