package com.felipeschoffen.onbudget.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    object OnBoarding {

        @Serializable
        object Welcome

        @Serializable
        object Auth {

            @Serializable
            object Login

            @Serializable
            object Register {

                @Serializable
                object Verification

                @Serializable
                object PIN
            }

            @Serializable
            object ForgotPassword {

                @Serializable
                object EmailSent
            }

        }
    }

    @Serializable
    object Home

    @Serializable
    object CreateFinancialAccount
}
