package com.felipeschoffen.montrabudgetapp.di

import android.content.Context
import com.felipeschoffen.montrabudgetapp.domain.util.ErrorMessages
import com.felipeschoffen.montrabudgetapp.domain.util.ResourceProvider
import com.felipeschoffen.montrabudgetapp.domain.validations.EmailValidator
import com.felipeschoffen.montrabudgetapp.domain.validations.NameValidator
import com.felipeschoffen.montrabudgetapp.domain.validations.PasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

    @Provides
    fun provideErrorMessages(resourceProvider: ResourceProvider): ErrorMessages {
        return ErrorMessages(resourceProvider)
    }

    @Provides
    fun provideNameValidator(errorMessages: ErrorMessages): NameValidator {
        return NameValidator(errorMessages)
    }

    @Provides
    fun provideEmailValidator(errorMessages: ErrorMessages): EmailValidator {
        return EmailValidator(errorMessages)
    }

    @Provides
    fun providePasswordValidator(errorMessages: ErrorMessages): PasswordValidator {
        return PasswordValidator(errorMessages)
    }
}