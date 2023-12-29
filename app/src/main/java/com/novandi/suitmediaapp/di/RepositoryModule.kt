package com.novandi.suitmediaapp.di

import com.novandi.suitmediaapp.data.repository.UserRepositoryImpl
import com.novandi.suitmediaapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository: UserRepositoryImpl): UserRepository
}