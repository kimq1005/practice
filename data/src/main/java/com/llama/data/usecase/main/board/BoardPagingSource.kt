package com.llama.data.usecase.main.board

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.llama.data.model.toDomain
import com.llama.data.retrofit.BoardService
import com.llama.domain.model.Board
import javax.inject.Inject

class BoardPagingSource @Inject constructor(
    private val boardService: BoardService
): PagingSource<Int, Board>() {
    override fun getRefreshKey(state: PagingState<Int, Board>): Int? {
        //혹시라도 화면을 무효화하고 리프래쉬 하는 경우 어디부터 데이터를 가져올 건지 ㅇㅇ
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Board> {
        try {
            val page = params.key ?: 1
            val loadSize = params.loadSize

            val response = boardService.getBoards(
                page = page,
                size = loadSize
            )

            val size = response.data.size

            return LoadResult.Page(
                data = response.data.map { it.toDomain() },
                prevKey = null,
                nextKey = if (size == loadSize) page + 1 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}