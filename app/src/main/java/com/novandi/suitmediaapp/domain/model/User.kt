package com.novandi.suitmediaapp.domain.model

data class User(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<UserData>
)

data class UserData(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)