package com.llama.data.di

import com.llama.data.usecase.ClearTokenUseCaseImpl
import com.llama.data.usecase.GetTokenUseCaseImpl
import com.llama.data.usecase.LoginUseCaseImpl
import com.llama.data.usecase.SetTokenUseCaseImpl
import com.llama.data.usecase.SignUpUseCaseImpl
import com.llama.data.usecase.main.setting.GetMyUserUseCaseImpl
import com.llama.domain.usecase.login.ClearTokenUseCase
import com.llama.domain.usecase.login.GetTokenUseCase
import com.llama.domain.usecase.login.LoginUseCase
import com.llama.domain.usecase.login.SetTokenUseCase
import com.llama.domain.usecase.login.SignUpUseCase
import com.llama.domain.usecase.main.setting.GetMyUserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    abstract fun bindLoginUseCase(useCase: LoginUseCaseImpl): LoginUseCase

    @Binds
    abstract fun bindSignUpUseCase(useCase: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    abstract fun bindGetTokenUseCase(useCase: GetTokenUseCaseImpl): GetTokenUseCase

    @Binds
    abstract fun bindSetTokenUseCase(useCase: SetTokenUseCaseImpl): SetTokenUseCase

    @Binds
    abstract fun bindClearTokenUseCase(useCase: ClearTokenUseCaseImpl): ClearTokenUseCase

    @Binds
    abstract fun bindGetMyUserUseCase(useCase: GetMyUserUseCaseImpl): GetMyUserUseCase
}