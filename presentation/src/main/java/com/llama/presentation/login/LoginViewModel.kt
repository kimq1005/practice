package com.llama.presentation.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llama.domain.usecase.login.LoginUseCase
import com.llama.domain.usecase.login.SetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setTokenUseCase: SetTokenUseCase,
) : ViewModel(), ContainerHost<LoginState, LoginSideEffect> {

    override val container: Container<LoginState, LoginSideEffect> = container(
        initialState = LoginState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    postSideEffect(LoginSideEffect.Toast(message = throwable.message.orEmpty()))
                }
            }
        }
    )

    fun onLoginClick() = intent {
        val id = state.id
        val password = state.password

        val token = loginUseCase(
            id = id,
            password = password
        ).getOrThrow()

        setTokenUseCase(token)

        postSideEffect(LoginSideEffect.Toast(message = "로그인 성공"))
        postSideEffect(LoginSideEffect.NavigateToMainActivity)
    }

    fun onIdChange(id: String) = blockingIntent {
        reduce {
            state.copy(id = id)
        }
    }

    fun onPasswordChange(password: String) = blockingIntent {
        reduce {
            state.copy(password = password)
        }
    }
}

@Immutable
data class LoginState(
    val id: String = "",
    val password: String = ""
)

sealed interface LoginSideEffect {
    class Toast(val message: String): LoginSideEffect
    object NavigateToMainActivity: LoginSideEffect
}