package com.novandi.suitmediaapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.novandi.suitmediaapp.data.source.RemoteDataSource
import com.novandi.suitmediaapp.domain.model.UserData
import com.novandi.suitmediaapp.util.UserMapper

class UserPagingSource(
    private val remoteDataSource: RemoteDataSource
): PagingSource<Int, UserData>() {
    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        return try {
            val page = params.key ?: 1
            val response = remoteDataSource.getUsers(page = page, perPage = params.loadSize)
            val result = UserMapper.mapResponseToDomain(response)

            LoadResult.Page(
                data = result.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}