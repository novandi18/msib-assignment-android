package com.novandi.suitmediaapp.domain.usecase

import androidx.paging.PagingData
import com.novandi.suitmediaapp.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getUsers(): Flow<PagingData<UserData>>
}