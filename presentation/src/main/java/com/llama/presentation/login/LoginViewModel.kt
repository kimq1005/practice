package com.llama.presentation.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llama.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel(), ContainerHost<LoginState, LoginSideEffect> {

    override val container: Container<LoginState, LoginSideEffect> = container(
        initialState = LoginState()
    )

    fun onLoginClick() = intent {
        val id = state.id
        val password = state.password

        viewModelScope.launch {
            loginUseCase(
                id = id,
                password = password
            )
        }
    }

    fun onIdChange(id: String) = intent {
        reduce {
            state.copy(id = id)
        }
    }

    fun onPasswordChange(password: String) {
        intent {
            reduce {
                state.copy(password = password)
            }
        }
    }
}

@Immutable
data class LoginState(
    val id: String = "",
    val password: String = ""
)

sealed interface LoginSideEffect