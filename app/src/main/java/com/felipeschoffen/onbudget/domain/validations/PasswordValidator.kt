package com.felipeschoffen.onbudget.domain.validations

import com.felipeschoffen.onbudget.domain.core.Constants
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.PasswordInputError

class PasswordValidator {

    fun execute(password: String): Result<Unit, PasswordInputError> {
        if (password.isBlank())
            return Result.Error(PasswordInputError.BLANK)

        if (password.length < Constants.PASSWORD_MIN_LENGTH)
            return Result.Error(PasswordInputError.SHORT_LENGTH)

        return Result.Success(Unit)
    }
}