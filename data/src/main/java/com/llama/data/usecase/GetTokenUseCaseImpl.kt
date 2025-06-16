package com.llama.data.usecase

import com.llama.data.UserDataStore
import com.llama.domain.usecase.login.GetTokenUseCase
import javax.inject.Inject

class GetTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
): GetTokenUseCase {
    override suspend fun invoke(): String? {
        return userDataStore.getToken()
    }
}