package com.llama.data.usecase.main.setting

import com.llama.data.model.UpdateMyInfoParam
import com.llama.data.retrofit.UserService
import com.llama.domain.usecase.main.setting.GetMyUserUseCase
import com.llama.domain.usecase.main.setting.SetMyUserNameUseCase
import javax.inject.Inject

class SetMyUserNameUseCaseImpl @Inject constructor(
    private val userService: UserService,
    private val getMyUserUseCase: GetMyUserUseCase,
) : SetMyUserNameUseCase {
    override suspend fun invoke(
        username: String,
        profileImageUrl: String?
    ): Result<Unit> = runCatching {
        val user = getMyUserUseCase().getOrThrow()

        val requestBody = UpdateMyInfoParam(
            userName = username,
            profileFilePath = user.profileImage.orEmpty(),
            extraUserInfo = ""
        ).toRequestBody()

        userService.pathMyPage(
            requestBody
        )
    }
}