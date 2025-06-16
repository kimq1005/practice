package com.llama.data.usecase

import com.llama.data.UserDataStore
import com.llama.domain.usecase.login.SetTokenUseCase
import javax.inject.Inject

class SetTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore,
) : SetTokenUseCase {
    override suspend fun invoke(token: String) {
        userDataStore.setToken(token)
    }
}