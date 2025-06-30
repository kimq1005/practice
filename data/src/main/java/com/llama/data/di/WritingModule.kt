package com.llama.data.di

import com.llama.data.usecase.main.writing.GetImageListUseCaseImpl
import com.llama.data.usecase.main.writing.PostBoardUseCaseImpl
import com.llama.domain.usecase.main.writing.GetImageListUseCase
import com.llama.domain.usecase.main.writing.PostBoardUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class WritingModule {

    @Binds
    abstract fun bindGetImageUseCase(useCase: GetImageListUseCaseImpl): GetImageListUseCase

    @Binds
    abstract fun bindPostBoardUseCase(useCase: PostBoardUseCaseImpl): PostBoardUseCase
}