package com.felipeschoffen.montrabudgetapp.domain.util

import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.core.error.EmailInputError
import com.felipeschoffen.montrabudgetapp.core.error.Error
import com.felipeschoffen.montrabudgetapp.core.error.NameInputError
import com.felipeschoffen.montrabudgetapp.core.error.PasswordInputError
import com.felipeschoffen.montrabudgetapp.core.error.RegisterError

class ErrorMessages(
    private val resourceProvider: ResourceProvider
) {
    fun getMessage(error: Error): String {

        val resourceId = when (error) {
            EmailInputError.BLANK -> R.string.error_blank_input
            EmailInputError.INVALID -> R.string.error_invalid_email
            PasswordInputError.SHORT_LENGTH -> R.string.error_short_password
            PasswordInputError.BLANK -> R.string.error_blank_input
            NameInputError.BLANK -> R.string.error_blank_input
            NameInputError.SHORT -> R.string.error_short_name
            RegisterError.USER_ALREADY_REGISTERED -> R.string.error_email_already_in_use
            RegisterError.UNKNOWN -> R.string.error_unknown
        }

        return resourceProvider.getString(resourceId)
    }
}