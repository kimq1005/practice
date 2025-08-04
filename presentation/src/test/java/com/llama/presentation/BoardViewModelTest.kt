package com.llama.presentation

import androidx.paging.PagingData
import com.llama.domain.model.Board
import com.llama.domain.usecase.main.board.DeleteBoardUseCase
import com.llama.domain.usecase.main.board.GetBoardUseCase
import com.llama.presentation.main.board.BoardViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Before
import org.junit.Test

class BoardViewModelTest {
    private lateinit var viewModel: BoardViewModel

    @Before
    fun setUp() {
        val getBoardUseCase = FakeGetBoardUseCase()
        val deleteBoardUseCase = FakeDeleteBoardUseCase()
        viewModel = BoardViewModel(
            getBoardUseCase,
            deleteBoardUseCase
        )
    }

    class FakeGetBoardUseCase : GetBoardUseCase {
        override suspend fun invoke(): Result<Flow<PagingData<Board>>> = runCatching {
            emptyFlow()
        }
    }

    class FakeDeleteBoardUseCase : DeleteBoardUseCase {
        override suspend fun invoke(boardId: Long): Result<Long> = runCatching {
            -1
        }
    }
}