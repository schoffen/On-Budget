package com.felipeschoffen.montrabudgetapp.domain.validations

import android.util.Patterns
import com.felipeschoffen.montrabudgetapp.domain.util.ErrorMessages
import com.felipeschoffen.montrabudgetapp.domain.util.InputError
import javax.inject.Inject

class EmailValidator @Inject constructor(
    private val errorMessages: ErrorMessages
) {

    fun execute(email: String): ValidationResult {
        if (email.isBlank())
            return ValidationResult(successful = false, errorMessage = errorMessages.getMessage(InputError.Blank))

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return ValidationResult(successful = false, errorMessage = errorMessages.getMessage(InputError.EmailInvalid))

        return ValidationResult(successful = true)
    }
}