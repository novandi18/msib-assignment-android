package com.novandi.suitmediaapp.di

import com.novandi.suitmediaapp.domain.usecase.UserInteractor
import com.novandi.suitmediaapp.domain.usecase.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase
}