package com.llama.domain.usecase.login

interface GetTokenUseCase {
    suspend operator fun invoke(): String?
}