package com.felipeschoffen.montrabudgetapp.domain.validations

import com.felipeschoffen.montrabudgetapp.domain.core.Constants
import com.felipeschoffen.montrabudgetapp.domain.util.ErrorMessages
import com.felipeschoffen.montrabudgetapp.domain.util.InputError
import javax.inject.Inject

class PasswordValidator @Inject constructor(
    private val errorMessages: ErrorMessages
) {

    fun execute(password: String): ValidationResult {
        if (password.isBlank())
            return ValidationResult(successful = false, errorMessage = errorMessages.getMessage(InputError.Blank))

        if (password.length < Constants.PASSWORD_MIN_LENGTH)
            return ValidationResult(successful = false, errorMessage = errorMessages.getMessage(InputError.PasswordShortLength))

        return ValidationResult(successful = true)
    }
}