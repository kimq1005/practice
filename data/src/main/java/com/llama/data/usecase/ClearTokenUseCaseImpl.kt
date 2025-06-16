package com.llama.data.usecase

import com.llama.data.UserDataStore
import com.llama.domain.usecase.login.ClearTokenUseCase
import javax.inject.Inject

class ClearTokenUseCaseImpl @Inject constructor(
    private val dataStore: UserDataStore
) : ClearTokenUseCase{
    override suspend fun invoke() {
        return dataStore.clear()
    }
}