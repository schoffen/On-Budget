package com.felipeschoffen.montrabudgetapp.domain.validations

import com.felipeschoffen.montrabudgetapp.domain.core.Constants
import com.felipeschoffen.montrabudgetapp.core.Result
import com.felipeschoffen.montrabudgetapp.core.error.PasswordInputError

class PasswordValidator {

    fun execute(password: String): Result<Unit, PasswordInputError> {
        if (password.isBlank())
            return Result.Error(PasswordInputError.BLANK)

        if (password.length < Constants.PASSWORD_MIN_LENGTH)
            return Result.Error(PasswordInputError.SHORT_LENGTH)

        return Result.Success(Unit)
    }
}