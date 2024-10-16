package com.felipeschoffen.onbudget.domain.util

import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.core.error.AuthError
import com.felipeschoffen.onbudget.core.error.EmailInputError
import com.felipeschoffen.onbudget.core.error.Error
import com.felipeschoffen.onbudget.core.error.LoginError
import com.felipeschoffen.onbudget.core.error.NameInputError
import com.felipeschoffen.onbudget.core.error.PasswordInputError
import com.felipeschoffen.onbudget.core.error.RegisterError

class ErrorMessages(
    private val resourceProvider: ResourceProvider
) {
    fun getErrorMessage(error: Error): String {

        val resourceId = when (error) {
            EmailInputError.BLANK -> R.string.error_blank_input
            EmailInputError.INVALID -> R.string.error_invalid_email
            PasswordInputError.SHORT_LENGTH -> R.string.error_short_password
            PasswordInputError.BLANK -> R.string.error_blank_input
            NameInputError.BLANK -> R.string.error_blank_input
            NameInputError.SHORT -> R.string.error_short_name
            RegisterError.USER_ALREADY_REGISTERED -> R.string.error_email_already_in_use
            RegisterError.TERMS_NOT_ACCEPTED -> R.string.error_terms_not_accepted
            RegisterError.UNKNOWN -> R.string.error_unknown
            AuthError.EMAIL_NOT_VERIFIED -> R.string.error_email_not_verified
            AuthError.USER_NOT_LOGGED_IN -> R.string.error_user_not_sign_in
            LoginError.INVALID_USER -> R.string.error_invalid_user
            LoginError.INVALID_CREDENTIALS -> R.string.error_invalid_credentials
            LoginError.TOO_MANY_REQUESTS -> R.string.error_many_requests
            LoginError.DISABLED_USER -> R.string.error_disabled_user
            LoginError.SERVER_NETWORK_CONNECTION -> R.string.error_database_network_connection
            LoginError.UNKNOWN -> R.string.error_unknown
        }

        return resourceProvider.getString(resourceId)
    }
}