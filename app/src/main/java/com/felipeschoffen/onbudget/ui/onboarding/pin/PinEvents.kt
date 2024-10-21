package com.felipeschoffen.onbudget.ui.onboarding.pin

sealed class PinEvents {
    data object SuccessfullyChecked : PinEvents()
    data object CheckFailed : PinEvents()
    data class ShowMessage(val message: String) : PinEvents()
}