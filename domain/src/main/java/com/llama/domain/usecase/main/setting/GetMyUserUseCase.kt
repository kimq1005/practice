package com.llama.domain.usecase.main.setting

import com.llama.domain.model.User

interface GetMyUserUseCase {
    suspend operator fun invoke(): Result<User>
}