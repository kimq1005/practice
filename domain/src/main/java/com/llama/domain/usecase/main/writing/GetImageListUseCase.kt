package com.llama.domain.usecase.main.writing

import com.llama.domain.model.Image

interface GetImageListUseCase {
    suspend operator fun invoke(): List<Image>
}