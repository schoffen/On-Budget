package com.felipeschoffen.onbudget.core.error

import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.domain.util.ResourceProvider

enum class AuthError : Error {
    EMAIL_NOT_VERIFIED,
    USER_NOT_LOGGED_IN
}

fun AuthError.toString(resourceProvider: ResourceProvider): String {
    val resId = when(this) {
        AuthError.EMAIL_NOT_VERIFIED -> R.string.error_email_not_verified
        AuthError.USER_NOT_LOGGED_IN -> R.string.error_user_not_sign_in
    }

    return resourceProvider.getString(resId)
}