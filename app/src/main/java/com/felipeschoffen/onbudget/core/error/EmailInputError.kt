package com.felipeschoffen.onbudget.core.error

import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.domain.util.ResourceProvider

enum class EmailInputError : Error {
    BLANK,
    INVALID
}

fun EmailInputError.toString(resourceProvider: ResourceProvider): String {
    val resId = when(this) {
        EmailInputError.BLANK -> R.string.error_blank_input
        EmailInputError.INVALID -> R.string.error_invalid_email
    }

    return resourceProvider.getString(resId)
}