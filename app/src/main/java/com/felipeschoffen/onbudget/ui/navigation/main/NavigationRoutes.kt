package com.felipeschoffen.onbudget.ui.navigation.main

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    object OnBoarding {

        @Serializable
        object Welcome

        @Serializable
        object Login

        @Serializable
        object Register {

            @Serializable
            object CreateUser

            @Serializable
            object Verification

            @Serializable
            object SetupPin

            @Serializable
            object SetupFinancialAccount {

                @Serializable
                object Introduction

                @Serializable
                object Create

                @Serializable
                object AllSet
            }
        }

        @Serializable
        object ForgotPassword {

            @Serializable
            object Request

            @Serializable
            data class EmailSent(val email: String)
        }
    }

    @Serializable
    object Main {

        @Serializable
        object Pin

        @Serializable
        object Home

        @Serializable
        object Transactions

        @Serializable
        object Budgets

        @Serializable
        object Profile
    }
}
