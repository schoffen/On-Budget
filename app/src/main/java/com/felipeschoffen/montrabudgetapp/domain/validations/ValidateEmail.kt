package com.felipeschoffen.montrabudgetapp.domain.validations

import android.util.Patterns
import com.felipeschoffen.montrabudgetapp.domain.util.InputError

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if (email.isBlank() || email.isEmpty())
            return ValidationResult(successful = false, error = InputError.Blank)

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return ValidationResult(successful = false, error = InputError.Invalid)

        return ValidationResult(successful = true)
    }
}