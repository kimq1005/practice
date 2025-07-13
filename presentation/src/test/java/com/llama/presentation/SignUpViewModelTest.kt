package com.llama.presentation

import com.llama.domain.usecase.login.SignUpUseCase
import com.llama.presentation.login.SignUpSideEffect
import com.llama.presentation.login.SignUpViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private val testId = "llama"
private val testPassword = "1234"
private val testUsername = "kim"

class SignUpViewModelTest {
    lateinit var signUpUseCase: FakeSignUpUseCase
    lateinit var viewModel: SignUpViewModel

    @Before
    fun setUp() {
        signUpUseCase = FakeSignUpUseCase()
        viewModel = SignUpViewModel(signUpUseCase)
    }

    @Test
    fun 입력된_아이디_상태_테스트() {
        Assert.assertEquals(viewModel.container.stateFlow.value.id, "")
        viewModel.onIdChange(testId)
        Assert.assertEquals(viewModel.container.stateFlow.value.id, testId)
    }

    @Test
    fun 입력된_패스워드_상태_테스트() {
        Assert.assertEquals(viewModel.container.stateFlow.value.password, "")
        viewModel.onPasswordChange(testPassword)
        Assert.assertEquals(viewModel.container.stateFlow.value.password, testPassword)
    }

    @Test
    fun 회원가입_버튼_클릭_실패_테스트() = runTest {
        val vm = SignUpViewModel(signUpUseCase)
        vm.onIdChange(testId)
        vm.onPasswordChange(testPassword)

        vm.onSignUpClick()
        val sideEffect = vm.container.sideEffectFlow.first()
        Assert.assertEquals((sideEffect as? SignUpSideEffect.Toast)?.message, "패스워드를 다시 확인해주세요.")
    }

    @Test
    fun 회원가입_버튼_클릭_성공_테스트() = runTest {
        val vm = SignUpViewModel(signUpUseCase)
        vm.onIdChange(testId)
        vm.onPasswordChange(testPassword)
        vm.onRepeatPasswordChange(testPassword)
        vm.onUsernameChange(testUsername)
        vm.onSignUpClick()
        val sideEffect = vm.container.sideEffectFlow.first()
        Assert.assertTrue(sideEffect is SignUpSideEffect.NavigateToLoginScreen)
    }

    @Test
    fun 회원가입_버튼_체킹_테스트() = runTest {
        viewModel.onIdChange(testId)
        viewModel.onPasswordChange(testPassword)
        viewModel.onRepeatPasswordChange(testPassword)
        viewModel.onUsernameChange(testUsername)

        val state = viewModel.container.stateFlow.first { it.isBtnValidationCheck }
        Assert.assertTrue(state.isBtnValidationCheck)
    }

    class FakeSignUpUseCase : SignUpUseCase {
        override suspend fun invoke(
            id: String,
            username: String,
            password: String,
        ): Result<Boolean> = runCatching {
            id == testId &&
                    username == testUsername &&
                    password == testPassword
        }
    }
}