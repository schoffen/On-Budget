package com.felipeschoffen.montrabudgetapp.domain.util

import com.felipeschoffen.montrabudgetapp.R

class ErrorMessages(
    private val resourceProvider: ResourceProvider
) {
    fun getMessage(error: InputError): String {

        val resourceId = when (error) {
            InputError.Blank -> R.string.error_blank_input
            InputError.EmailInvalid -> R.string.error_invalid_email
            InputError.NameShortLength -> R.string.error_short_name
            InputError.PasswordShortLength -> R.string.error_short_password
        }

        return resourceProvider.getString(resourceId)
    }
}