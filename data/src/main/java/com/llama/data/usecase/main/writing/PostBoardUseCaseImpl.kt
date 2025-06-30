package com.llama.data.usecase.main.writing

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.llama.data.model.BoardParcel
import com.llama.data.service.PostingService
import com.llama.domain.model.Image
import com.llama.domain.usecase.main.writing.PostBoardUseCase
import javax.inject.Inject

class PostBoardUseCaseImpl @Inject constructor(
    private val context: Context
) : PostBoardUseCase {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun invoke(
        title: String,
        content: String,
        images: List<Image>
    ) {
        val board = BoardParcel(
            title = title,
            content = content,
            images = images
        )

       context.startForegroundService(
           Intent(
               context,
               PostingService::class.java
           ).apply {
               putExtra(PostingService.EXTRA_BOARD, board)
           }
       )
    }
}