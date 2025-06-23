package com.llama.data.usecase.main.setting

import com.llama.data.model.UpdateMyInfoParam
import com.llama.data.retrofit.UserService
import com.llama.domain.usecase.main.setting.GetMyUserUseCase
import com.llama.domain.usecase.main.setting.SetMyUserUseCase
import javax.inject.Inject

class SetMyUserUseCaseImpl @Inject constructor(
    private val userService: UserService,
    private val getMyUserUseCase: GetMyUserUseCase,
) : SetMyUserUseCase {
    override suspend fun invoke(
        username: String?,
        profileImageUrl: String?
    ): Result<Unit> = runCatching {
        val user = getMyUserUseCase().getOrThrow()

        val requestBody = UpdateMyInfoParam(
            userName = username ?: user.username,
            profileFilePath = profileImageUrl ?: user.profileImageUrl.orEmpty(),
            extraUserInfo = ""
        ).toRequestBody()

        userService.pathMyPage(
            requestBody
        )
    }
}