package com.novandi.suitmediaapp.util

import com.novandi.suitmediaapp.data.source.response.UserResponse
import com.novandi.suitmediaapp.domain.model.User
import com.novandi.suitmediaapp.domain.model.UserData

object UserMapper {
    fun mapResponseToDomain(input: UserResponse): User {
        val user = input.data.map { data ->
            UserData(
                id = data.id,
                email = data.email,
                firstName = data.firstName,
                lastName = data.lastName,
                avatar = data.avatar
            )
        }

        return User(
            page = input.page,
            perPage = input.perPage,
            total = input.total,
            totalPages = input.totalPages,
            data = user
        )
    }
}