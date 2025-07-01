package com.llama.data.usecase.main.board

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.llama.domain.model.Board
import com.llama.domain.usecase.main.board.GetBoardUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Provider

class GetBoardUseCaseImpl @Inject constructor(
    private val pagingSource: Provider<BoardPagingSource>,
) : GetBoardUseCase {
    override suspend fun invoke(): Result<Flow<PagingData<Board>>> = runCatching {
        Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                pagingSource.get()
            }
        ).flow
    }
}