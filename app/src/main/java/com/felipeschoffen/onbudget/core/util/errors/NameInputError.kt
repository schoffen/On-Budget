package com.felipeschoffen.onbudget.core.util.errors

import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.domain.util.ResourceProvider

enum class NameInputError : Error {
    BLANK,
    SHORT
}

fun NameInputError.toString(resourceProvider: ResourceProvider): String {
    val resId = when(this) {
        NameInputError.BLANK -> R.string.error_blank_input
        NameInputError.SHORT -> R.string.error_short_name
    }

    return resourceProvider.getString(resId)
}