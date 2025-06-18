package com.llama.domain.usecase.file

import com.llama.domain.model.Image

interface UploadImageUseCase {
    suspend operator fun invoke(
        image: Image,
    ): Result<String>
}