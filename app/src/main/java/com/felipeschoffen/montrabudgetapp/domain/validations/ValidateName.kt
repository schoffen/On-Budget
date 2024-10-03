package com.felipeschoffen.montrabudgetapp.domain.validations

import com.felipeschoffen.montrabudgetapp.domain.core.Constants
import com.felipeschoffen.montrabudgetapp.domain.util.InputError

class ValidateName {

    fun execute(name: String): ValidationResult {
        if (name.isBlank() || name.isEmpty())
            return ValidationResult(successful = false, error = InputError.Blank)

        if (name.length < Constants.NAME_MIN_LENGTH)
            return ValidationResult(successful = false, error = InputError.ShortLength)

        return ValidationResult(successful = true)
    }
}