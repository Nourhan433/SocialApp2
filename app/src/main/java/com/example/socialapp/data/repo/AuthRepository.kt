package com.example.socialapp.data.repo

import com.example.socialapp.domain.model.User
import kotlinx.coroutines.flow.Flow

sealed class AuthError(message: String) : Exception(message) {
    object EmailAlreadyUsed : AuthError("Email is already registered")
    object InvalidCredentials : AuthError("Invalid email or password")
}

interface AuthRepository {
    suspend fun signUp(username: String, email: String, password: String): User
    suspend fun signIn(email: String, password: String): User
    suspend fun signOut()
    val currentUserFlow: Flow<User?>
}