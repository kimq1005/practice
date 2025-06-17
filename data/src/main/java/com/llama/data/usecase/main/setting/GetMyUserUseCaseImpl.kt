package com.llama.data.usecase.main.setting

import com.llama.data.model.toDomainModel
import com.llama.data.retrofit.UserService
import com.llama.domain.model.User
import com.llama.domain.usecase.main.setting.GetMyUserUseCase
import javax.inject.Inject

class GetMyUserUseCaseImpl @Inject constructor(
    private val userService: UserService,
) : GetMyUserUseCase {
    override suspend fun invoke(): Result<User> = runCatching {
        userService.myPage().data.toDomainModel()
    }
}