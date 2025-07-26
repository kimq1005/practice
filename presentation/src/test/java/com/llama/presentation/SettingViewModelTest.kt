package com.llama.presentation

import com.llama.domain.model.User
import com.llama.domain.usecase.login.ClearTokenUseCase
import com.llama.domain.usecase.main.setting.GetMyUserUseCase
import com.llama.domain.usecase.main.setting.SetMyUserUseCase
import com.llama.presentation.main.setting.SettingViewModel

class SettingViewModelTest {
    lateinit var viewModel: SettingViewModel

    class FakeClearTokenUseCase : ClearTokenUseCase {
        override suspend fun invoke(): Result<Unit> = runCatching {

        }
    }

    class FakeGetMyUserUseCase : GetMyUserUseCase {
        override suspend fun invoke(): Result<User> = runCatching {
            User(
                id = 0,
                loginId = "",
                username = "",
                profileImageUrl = ""
            )
        }
    }

    class FakeSetMyUserUseCase : SetMyUserUseCase {
        override suspend fun invoke(
            username: String?,
            profileImageUrl: String?,
        ): Result<Unit> = runCatching {

        }
    }
}