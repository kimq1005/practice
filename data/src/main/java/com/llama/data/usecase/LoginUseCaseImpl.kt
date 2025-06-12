package com.llama.data.usecase

import com.llama.domain.usecase.login.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor() : LoginUseCase {
    override suspend fun invoke(
        id: String,
        password: String
    ): Result<String> = runCatching {
       "token"
    }
}