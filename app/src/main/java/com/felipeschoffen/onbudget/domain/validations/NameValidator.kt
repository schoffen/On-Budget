package com.felipeschoffen.onbudget.domain.validations

import com.felipeschoffen.onbudget.core.Constants
import com.felipeschoffen.onbudget.core.util.Result
import com.felipeschoffen.onbudget.core.util.errors.NameInputError

class NameValidator {

    fun execute(name: String): Result<Unit, NameInputError> {

        if (name.isBlank())
            return Result.Error(NameInputError.BLANK)

        if (name.length < Constants.NAME_MIN_LENGTH)
            return Result.Error(NameInputError.SHORT)

        return Result.Success(Unit)
    }
}