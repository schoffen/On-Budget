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
                data class EmailSent(val email: String)
            }
        }

        @Serializable
        object CreateFinancialAccount {

            @Serializable
            object Introduction

            @Serializable
            object Create

            @Serializable
            object AllSet
        }
    }

    @Serializable
    object Home
}
