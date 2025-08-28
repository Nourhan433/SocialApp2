package com.example.socialapp.domain.usecase

import com.example.socialapp.data.repo.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideSignInUseCase(authRepository: AuthRepository) = SignInUseCase(authRepository)

    @Provides
    fun provideSignUPUseCase(authRepository: AuthRepository)= SignUpUseCase(authRepository)
}