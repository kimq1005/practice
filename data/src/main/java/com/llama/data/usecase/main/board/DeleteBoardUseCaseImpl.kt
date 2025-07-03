package com.llama.data.usecase.main.board

import android.util.Log
import com.llama.data.retrofit.BoardService
import com.llama.domain.usecase.main.board.DeleteBoardUseCase
import javax.inject.Inject

class DeleteBoardUseCaseImpl @Inject constructor(
    private val boardService: BoardService,
) : DeleteBoardUseCase {
    override suspend fun invoke(
        boardId: Long,
    ): Result<Long> = runCatching {
        boardService.deleteBoard(boardId).data
    }.onFailure {
        Log.e("TAG", "deleteBoard error: $it")
    }
}