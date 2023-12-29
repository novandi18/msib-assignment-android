package com.novandi.suitmediaapp.domain.usecase

import androidx.paging.PagingData
import com.novandi.suitmediaapp.domain.model.UserData
import com.novandi.suitmediaapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userRepository: UserRepository
): UserUseCase {
    override fun getUsers(): Flow<PagingData<UserData>> = userRepository.getUsers()
}