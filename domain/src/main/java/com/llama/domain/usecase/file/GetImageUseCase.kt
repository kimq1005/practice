package com.llama.domain.usecase.file

import com.llama.domain.model.Image

interface GetImageUseCase {
    operator fun invoke(contentUri: String): Image?
}