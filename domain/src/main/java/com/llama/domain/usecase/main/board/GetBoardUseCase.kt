package com.llama.domain.usecase.main.board

import androidx.paging.PagingData
import com.llama.domain.model.Board
import kotlinx.coroutines.flow.Flow

interface GetBoardUseCase {
    suspend operator fun invoke(): Result<Flow<PagingData<Board>>>
}