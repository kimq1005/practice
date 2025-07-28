package com.llama.presentation

import com.llama.domain.model.User
import com.llama.domain.usecase.login.ClearTokenUseCase
import com.llama.domain.usecase.main.setting.GetMyUserUseCase
import com.llama.domain.usecase.main.setting.SetMyUserUseCase
import com.llama.domain.usecase.main.setting.SetProfileImageUseCase
import com.llama.presentation.main.setting.SettingSideEffect
import com.llama.presentation.main.setting.SettingViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private var testId = 1L
private var testUsername = "kim"
private var testLoginId = "q1005"
private var testChangeUsername = "changeMe"
private var isChange = false

class SettingViewModelTest {
    private lateinit var viewModel: SettingViewModel
    private lateinit var clearTokenUseCase: FakeClearTokenUseCase
    private lateinit var getMyUserUseCase: FakeGetMyUserUseCase
    private lateinit var setMyUserUseCase: FakeSetMyUserUseCase
    private lateinit var setProfileImageUseCase: FakeSetProfileImageUseCase
    private lateinit var getMyUserChangeUseCase: FakeGetMyUserChangeUseCase

    @Before
    fun setUp() {
        clearTokenUseCase = FakeClearTokenUseCase()
        getMyUserUseCase = FakeGetMyUserUseCase()
        setMyUserUseCase = FakeSetMyUserUseCase()
        setProfileImageUseCase = FakeSetProfileImageUseCase()
        getMyUserChangeUseCase = FakeGetMyUserChangeUseCase()

        viewModel = SettingViewModel(
            clearTokenUseCase,
            getMyUserUseCase,
            setMyUserUseCase,
            setProfileImageUseCase
        )
    }

    @Test
    fun `유저 정보 가져오기 테스트`() = runTest {
        isChange = false
        viewModel.load()
        val state = viewModel.container.stateFlow.first()
        Assert.assertEquals(state.username, testUsername)
    }

    @Test
    fun `로그아웃 버튼 클릭 테스트`() = runTest {
        viewModel.onLogoutClick()
        val sideEffect = viewModel.container.sideEffectFlow.first()
        Assert.assertTrue(sideEffect is SettingSideEffect.NavigateToLoginActivity)
    }

    @Test
    fun `유저 이름 변경 테스트` () = runTest {
        val vm = SettingViewModel(
            clearTokenUseCase,
            getMyUserChangeUseCase,
            setMyUserUseCase,
            setProfileImageUseCase
        )

        isChange = true
        viewModel.onUsernameChange(testChangeUsername)

        val state = viewModel.container.stateFlow.value
        Assert.assertEquals(state.username, testChangeUsername)
    }

    class FakeClearTokenUseCase : ClearTokenUseCase {
        override suspend fun invoke(): Result<Unit> = runCatching {

        }
    }

    class FakeGetMyUserUseCase : GetMyUserUseCase {
        override suspend fun invoke(): Result<User> = runCatching {
            User(
                id = testId,
                loginId = testLoginId,
                username = if (!isChange) testUsername else testChangeUsername,
                profileImageUrl = ""
            )
        }
    }

    class FakeGetMyUserChangeUseCase : GetMyUserUseCase {
        override suspend fun invoke(): Result<User> = runCatching {
            User(
                id = testId,
                loginId = testLoginId,
                username = testChangeUsername,
                profileImageUrl = ""
            )
        }
    }

    class FakeSetMyUserUseCase : SetMyUserUseCase {
        override suspend fun invoke(
            username: String?,
            profileImageUrl: String?,
        ): Result<Unit> = runCatching {

            Unit
        }
    }

    class FakeSetProfileImageUseCase: SetProfileImageUseCase {
        override suspend fun invoke(
            contentUri: String
        ): Result<Unit> = runCatching {
            Unit
        }
    }
}