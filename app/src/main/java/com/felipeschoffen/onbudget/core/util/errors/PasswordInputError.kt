package com.felipeschoffen.onbudget.core.util.errors

import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.domain.util.ResourceProvider

enum class PasswordInputError : Error {
    SHORT_LENGTH,
    BLANK
}

fun PasswordInputError.toString(resourceProvider: ResourceProvider): String {
    val resId = when(this) {
        PasswordInputError.SHORT_LENGTH -> R.string.error_short_password
        PasswordInputError.BLANK -> R.string.error_blank_input
    }

    return resourceProvider.getString(resId)
}