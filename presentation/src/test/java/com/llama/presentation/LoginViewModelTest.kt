package com.llama.presentation

import com.llama.domain.usecase.login.LoginUseCase
import com.llama.domain.usecase.login.SetTokenUseCase
import com.llama.presentation.login.LoginSideEffect
import com.llama.presentation.login.LoginViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {
    private lateinit var loginUseCase: FakeLoginUseCase
    private lateinit var setTokenUseCase: SetTokenUseCase
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel(
            loginUseCase = loginUseCase,
            setTokenUseCase = setTokenUseCase
        )
    }

    @Test
    fun `로그인 성공 테스트`() = runTest {
        val vm = LoginViewModel(
            loginUseCase = loginUseCase,
            setTokenUseCase = setTokenUseCase
        )

        vm.onIdChange("llama")
        vm.onPasswordChange("1234")

        viewModel.onLoginClick()
        val sideEffect = vm.container.sideEffectFlow.first()
        Assert.assertEquals(sideEffect as? LoginSideEffect.Toast, "로그인 성공")
    }

    class FakeLoginUseCase : LoginUseCase {
        override suspend fun invoke(
            id: String,
            password: String,
        ): Result<String> = runCatching {
            "테스트"
        }
    }
}