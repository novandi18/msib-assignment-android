package com.novandi.suitmediaapp.data.source

import com.novandi.suitmediaapp.data.source.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUsers(page: Int, perPage: Int) = apiService.getUsers(page, perPage)
}