package com.example.socialapp.domain.usecase

import com.example.socialapp.data.repo.AuthRepository
import com.example.socialapp.domain.model.User
//AuthRepository is data layer: it knows how to access
// the database, hash passwords, manage sessions.
//
//SignInUseCase is domain layer:
// it knows the business rule “how a user signs in”
// but doesn’t care about storage details.
//sepration of concern
class SignInUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): User =
        repo.signIn(email, password)
}