package com.example.urcast.data.remote.dto

data class LoginRequest(
    val email: String,
    val password: String
)

data class TokenResponse(
    val access_token: String,
    val token_type: String
)

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val username: String,
    val email: String,
    val user_id: Int,
    val role: String,
    val created_at: String
)