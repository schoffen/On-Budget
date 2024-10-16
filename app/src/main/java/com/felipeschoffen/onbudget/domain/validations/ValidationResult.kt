package com.felipeschoffen.onbudget.domain.validations

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
