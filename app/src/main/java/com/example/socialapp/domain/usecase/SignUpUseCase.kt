package com.example.socialapp.domain.usecase

import com.example.socialapp.data.repo.AuthRepository
import com.example.socialapp.domain.model.User

class SignUpUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String): User =
        repo.signUp(username, email, password)
}