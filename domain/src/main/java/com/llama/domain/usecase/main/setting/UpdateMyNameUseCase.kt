package com.llama.domain.usecase.main.setting

interface SetMyUserNameUseCase {
    suspend operator fun invoke(
         username: String,
         profileImageUrl: String?
    ): Result<Unit>
}