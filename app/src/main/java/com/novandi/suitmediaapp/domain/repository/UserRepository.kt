package com.novandi.suitmediaapp.domain.repository

import androidx.paging.PagingData
import com.novandi.suitmediaapp.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<UserData>>
}