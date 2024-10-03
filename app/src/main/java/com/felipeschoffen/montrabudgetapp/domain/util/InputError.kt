package com.felipeschoffen.montrabudgetapp.domain.util

sealed class InputError() {

    data object Blank : InputError()
    data object NameShortLength : InputError()
    data object PasswordShortLength : InputError()
    data object EmailInvalid : InputError()
}