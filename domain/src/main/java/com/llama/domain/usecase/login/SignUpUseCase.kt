package com.llama.domain.usecase.login

interface SignUpUseCase {
    suspend operator fun invoke(
        id: String,
        username: String,
        password: String
    ): Result<Boolean>
}