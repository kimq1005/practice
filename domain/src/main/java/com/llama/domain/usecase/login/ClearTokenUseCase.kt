package com.llama.domain.usecase.login

interface ClearTokenUseCase {
    suspend operator fun invoke()
}