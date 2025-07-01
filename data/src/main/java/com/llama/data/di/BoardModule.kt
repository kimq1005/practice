package com.llama.data.di

import com.llama.data.usecase.main.board.GetBoardUseCaseImpl
import com.llama.domain.usecase.main.board.GetBoardUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class BoardModule {

    @Binds
    abstract fun bindGetBoardUseCase(uc: GetBoardUseCaseImpl): GetBoardUseCase
}