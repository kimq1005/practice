package com.llama.domain.usecase.main.board

interface DeleteBoardUseCase {
    suspend operator fun invoke(
        boardId: Long
    ): Result<Long>
}