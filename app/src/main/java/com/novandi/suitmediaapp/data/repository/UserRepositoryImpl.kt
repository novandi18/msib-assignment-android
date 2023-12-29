package com.novandi.suitmediaapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.novandi.suitmediaapp.data.paging.UserPagingSource
import com.novandi.suitmediaapp.data.source.RemoteDataSource
import com.novandi.suitmediaapp.domain.model.UserData
import com.novandi.suitmediaapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteRemoteDataSource: RemoteDataSource
): UserRepository {
    override fun getUsers(): Flow<PagingData<UserData>> =
        Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = {
                UserPagingSource(remoteRemoteDataSource)
            }
        ).flow
}