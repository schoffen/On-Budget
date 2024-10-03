package com.felipeschoffen.montrabudgetapp.domain.validations

import com.felipeschoffen.montrabudgetapp.domain.core.Constants
import com.felipeschoffen.montrabudgetapp.domain.util.InputError

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if (password.isBlank() || password.isEmpty())
            return ValidationResult(successful = false, error = InputError.Blank)

        if (password.length < Constants.PASSWORD_MIN_LENGTH)
            return ValidationResult(successful = false, error = InputError.ShortLength)

        return ValidationResult(successful = true)
    }
}