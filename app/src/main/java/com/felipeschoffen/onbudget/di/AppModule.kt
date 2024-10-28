package com.felipeschoffen.onbudget.di

import android.content.Context
import com.felipeschoffen.onbudget.data.database.AuthDataSource
import com.felipeschoffen.onbudget.data.database.FirebaseDataSource
import com.felipeschoffen.onbudget.data.repository.AuthRepositoryImpl
import com.felipeschoffen.onbudget.domain.auth.PinHashing
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ResourceProvider
import com.felipeschoffen.onbudget.domain.validations.EmailValidator
import com.felipeschoffen.onbudget.domain.validations.NameValidator
import com.felipeschoffen.onbudget.domain.validations.PasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthDatabase(): AuthDataSource {
        return FirebaseDataSource
    }

    @Provides
    fun providePinHashing(): PinHashing {
        return PinHashing()
    }

    @Provides
    fun provideAuthRepository(authDataSource: AuthDataSource, pinHashing: PinHashing): AuthRepository {
        return AuthRepositoryImpl(authDataSource, pinHashing)
    }

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

    @Provides
    fun provideNameValidator(): NameValidator {
        return NameValidator()
    }

    @Provides
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator()
    }

    @Provides
    fun providePasswordValidator(): PasswordValidator {
        return PasswordValidator()
    }
}