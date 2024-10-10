package com.felipeschoffen.montrabudgetapp.domain.validations

import android.util.Patterns
import com.felipeschoffen.montrabudgetapp.core.Result
import com.felipeschoffen.montrabudgetapp.core.error.EmailInputError

class EmailValidator {

    fun execute(email: String): Result<Unit, EmailInputError> {
        if (email.isBlank())
            return Result.Error(EmailInputError.BLANK)

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return Result.Error(EmailInputError.INVALID)

        return Result.Success(Unit)
    }
}