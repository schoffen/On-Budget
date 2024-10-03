package com.felipeschoffen.montrabudgetapp.domain.validations

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
