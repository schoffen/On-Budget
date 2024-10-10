package com.felipeschoffen.montrabudgetapp.domain.validations

import com.felipeschoffen.montrabudgetapp.domain.core.Constants
import com.felipeschoffen.montrabudgetapp.core.Result
import com.felipeschoffen.montrabudgetapp.core.error.NameInputError

class NameValidator {

    fun execute(name: String): Result<Unit, NameInputError> {

        if (name.isBlank())
            return Result.Error(NameInputError.BLANK)

        if (name.length < Constants.NAME_MIN_LENGTH)
            return Result.Error(NameInputError.SHORT)

        return Result.Success(Unit)
    }
}