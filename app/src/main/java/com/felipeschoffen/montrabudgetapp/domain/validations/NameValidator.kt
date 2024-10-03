package com.felipeschoffen.montrabudgetapp.domain.validations

import com.felipeschoffen.montrabudgetapp.domain.core.Constants
import com.felipeschoffen.montrabudgetapp.domain.util.ErrorMessages
import com.felipeschoffen.montrabudgetapp.domain.util.InputError
import javax.inject.Inject

class NameValidator @Inject constructor(
    private val errorMessages: ErrorMessages
) {

    fun execute(name: String): ValidationResult {

        if (name.isBlank())
            return ValidationResult(
                successful = false,
                errorMessage = errorMessages.getMessage(InputError.Blank)
            )

        if (name.length < Constants.NAME_MIN_LENGTH)
            return ValidationResult(
                successful = false,
                errorMessage = errorMessages.getMessage(InputError.NameShortLength)
            )

        return ValidationResult(successful = true)
    }
}