package com.felipeschoffen.montrabudgetapp.domain.validations

import com.felipeschoffen.montrabudgetapp.domain.util.InputError

data class ValidationResult(
    val successful: Boolean,
    val error: InputError? = null
)
