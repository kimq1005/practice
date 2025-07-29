package com.llama.presentation

import androidx.core.net.toUri
import com.llama.domain.model.User
import com.llama.domain.usecase.login.ClearTokenUseCase
import com.llama.domain.usecase.main.setting.GetMyUserUseCase
import com.llama.domain.usecase.main.setting.SetMyUserUseCase
import com.llama.domain.usecase.main.setting.SetProfileImageUseCase
import com.llama.presentation.main.setting.SettingSideEffect
import com.llama.presentation.main.setting.SettingViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private var testId = 1L
private var testUsername = "kim"
private var testLoginId = "q1005"
private var testChangeUsername = "changeMe"
private var testImageUrl = "https://placehold.co/600x400"

class SettingViewModelTest {
    private lateinit var viewModel: SettingViewModel
    private lateinit var clearTokenUseCase: FakeClearTokenUseCase
    private lateinit var getMyUserUseCase: FakeGetMyUserUseCase
    private lateinit var setMyUserUseCase: FakeSetMyUserUseCase
    private lateinit var setProfileImageUseCase: FakeSetProfileImageUseCase

    @Before
    fun setUp() {
        clearTokenUseCase = FakeClearTokenUseCase()
        getMyUserUseCase = FakeGetMyUserUseCase()
        setMyUserUseCase = FakeSetMyUserUseCase()
        setProfileImageUseCase = FakeSetProfileImageUseCase()

        viewModel = SettingViewModel(
            clearTokenUseCase,
            getMyUserUseCase,
            setMyUserUseCase,
            setProfileImageUseCase
        )
    }

    @Test
    fun `유저 정보 가져오기 테스트`() = runTest {
        val vm = SettingViewModel(
            clearTokenUseCase,
            FakeGetMyUserUseCase(testUsername),
            setMyUserUseCase,
            setProfileImageUseCase
        )

        vm.load()

        val state = vm.container.stateFlow.first()
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
            FakeGetMyUserUseCase(testChangeUsername),
            setMyUserUseCase,
            setProfileImageUseCase
        )

        vm.onUsernameChange(testChangeUsername)

        val state = vm.container.stateFlow.first()
        Assert.assertEquals(state.username, testChangeUsername)
    }

    @Test
    fun `프로필 이미지 변경 테스트`() = runTest {
        val vm = SettingViewModel(
            clearTokenUseCase,
            FakeGetMyUserUseCase(
                username = testUsername,
                profileImageUrl = testImageUrl
            ),
            setMyUserUseCase,
            setProfileImageUseCase
        )

        vm.onImageChange(testImageUrl.toUri())

        val state = vm.container.stateFlow.value
        Assert.assertEquals(state.profileImageUrl, testImageUrl)
    }

    class FakeClearTokenUseCase : ClearTokenUseCase {
        override suspend fun invoke(): Result<Unit> = runCatching { }
    }

    class FakeGetMyUserUseCase(
        private val username: String = "",
        private val profileImageUrl: String = ""
    ) : GetMyUserUseCase {
        override suspend fun invoke(): Result<User> = runCatching {
            User(
                id = testId,
                loginId = testLoginId,
                username = username,
                profileImageUrl = profileImageUrl
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

    class FakeSetProfileImageUseCase: SetProfileImageUseCase {
        private var savedContentUri = ""

        override suspend fun invoke(
            contentUri: String
        ): Result<Unit> = runCatching {
            savedContentUri = contentUri
        }

        fun getContentUri(): String = savedContentUri
    }
}