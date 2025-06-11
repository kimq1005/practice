package com.llama.domain.usecase.login

interface SignUpUseCase {
    suspend operator fun invoke(
        id: String,
        username: String,
        passWord: String
    ): Result<Unit>
}