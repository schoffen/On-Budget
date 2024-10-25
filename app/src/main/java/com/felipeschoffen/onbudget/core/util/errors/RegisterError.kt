package com.felipeschoffen.onbudget.core.util.errors

import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.domain.util.ResourceProvider

enum class RegisterError : Error {
    USER_ALREADY_REGISTERED,
    TERMS_NOT_ACCEPTED,
    UNKNOWN
}

fun RegisterError.toString(resourceProvider: ResourceProvider): String {
    val resId = when(this) {
        RegisterError.USER_ALREADY_REGISTERED -> R.string.error_email_already_in_use
        RegisterError.TERMS_NOT_ACCEPTED -> R.string.error_terms_not_accepted
        RegisterError.UNKNOWN -> R.string.error_unknown
    }

    return resourceProvider.getString(resId)
}