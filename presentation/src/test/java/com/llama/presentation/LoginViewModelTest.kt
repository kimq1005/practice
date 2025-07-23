package com.llama.presentation

import com.llama.domain.usecase.login.LoginUseCase
import com.llama.domain.usecase.login.SetTokenUseCase
import com.llama.presentation.login.LoginSideEffect
import com.llama.presentation.login.LoginViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private val testId = "llama"
private val testPassword = "1234"

private val success = "success"
private val error = "error"

class LoginViewModelTest {
    private lateinit var loginUseCase: FakeLoginUseCase
    private lateinit var setTokenUseCase: SetTokenUseCase
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        loginUseCase = FakeLoginUseCase()
        setTokenUseCase = FakeSetTokenUseCase()

//        viewModel = LoginViewModel(
//            loginUseCase = loginUseCase,
//            setTokenUseCase = setTokenUseCase
//        )
    }

    @Test
    fun `로그인 성공 테스트`() = runTest {
        val test = loginUseCase(
            "llama",
            "1234"
        )

        Assert.assertEquals(test.getOrThrow(), success)
    }

    @Test
    fun `로그인 버튼 클릭 테스트`() = runTest {
        val viewModel = LoginViewModel(
            loginUseCase = loginUseCase,
            setTokenUseCase = setTokenUseCase
        )

        viewModel.onIdChange(testId)
        viewModel.onPasswordChange(testPassword)

        viewModel.onLoginClick()

        val sideEffect = viewModel.container.sideEffectFlow.first()
        Assert.assertTrue(sideEffect is LoginSideEffect.NavigateToMainActivity)
    }

    class FakeLoginUseCase : LoginUseCase {
        override suspend fun invoke(
            id: String,
            password: String,
        ): Result<String> = runCatching {
           if (id == testId && password == testPassword)
               success
           else
               error
        }
    }

    class FakeSetTokenUseCase : SetTokenUseCase {
        private var savedToken = ""

        override suspend fun invoke(token: String) {
            savedToken = token
        }
    }
}