package com.llama.domain.usecase.main.writing

import com.llama.domain.model.Image

interface PostBoardUseCase {
    suspend operator fun invoke(
        title: String,
        content: String,
        images: List<Image>,
    )
}