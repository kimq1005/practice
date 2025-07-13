package com.llama.data.usecase

import android.util.Log
import com.llama.data.model.SignUpParam
import com.llama.data.retrofit.UserService
import com.llama.domain.usecase.login.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val userService: UserService,
) : SignUpUseCase {
    override suspend fun invoke(
        id: String,
        username: String,
        password: String
    ): Result<Boolean> = runCatching {
        val requestBody = SignUpParam(
            loginId = id,
            name = username,
            password = password,
            extraUserInfo = "",
            profileFilePath = ""
        ).toRequestBody()
        userService.signUp(requestBody = requestBody).result == "SUCCESS"
    }.onFailure {
        Log.e("TAG", "테스트: $it")
    }
}