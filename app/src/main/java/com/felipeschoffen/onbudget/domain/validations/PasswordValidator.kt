package com.felipeschoffen.onbudget.domain.validations

import com.felipeschoffen.onbudget.core.Constants
import com.felipeschoffen.onbudget.core.util.Result
import com.felipeschoffen.onbudget.core.util.errors.PasswordInputError

class PasswordValidator {

    fun execute(password: String): Result<Unit, PasswordInputError> {
        if (password.isBlank())
            return Result.Error(PasswordInputError.BLANK)

        if (password.length < Constants.PASSWORD_MIN_LENGTH)
            return Result.Error(PasswordInputError.SHORT_LENGTH)

        return Result.Success(Unit)
    }
}