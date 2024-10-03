package com.felipeschoffen.montrabudgetapp.domain.util

sealed class InputError() {

    data object Blank : InputError()
    data object ShortLength : InputError()
    data object Invalid : InputError()
}