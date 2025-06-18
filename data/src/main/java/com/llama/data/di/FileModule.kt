package com.llama.data.di

import com.llama.data.usecase.file.GetImageUseCaseImpl
import com.llama.data.usecase.file.GetInputStreamUseCaseImpl
import com.llama.data.usecase.file.UploadImageUseCaseImpl
import com.llama.domain.usecase.file.GetImageUseCase
import com.llama.domain.usecase.file.GetInputStreamUseCase
import com.llama.domain.usecase.file.UploadImageUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FileModule {

    @Binds
    abstract fun bindGetInputStreamUseCase(useCase: GetInputStreamUseCaseImpl): GetInputStreamUseCase

    @Binds
    abstract fun bindGetImageUseCase(useCase: GetImageUseCaseImpl): GetImageUseCase

    @Binds
    abstract fun bindUploadImageUseCase(useCase: UploadImageUseCaseImpl): UploadImageUseCase

}