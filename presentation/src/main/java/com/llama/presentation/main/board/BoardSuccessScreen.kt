package com.llama.presentation.main.board

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.llama.presentation.model.model.board.BoardCardModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun BoardSuccessScreen(
    modifier: Modifier = Modifier,
    viewModel: BoardViewModel = hiltViewModel(),
) {
    val state = viewModel.collectAsState().value
    var modelForDialog:BoardCardModel? by remember { mutableStateOf(null) }

    BoardScreen(
        boardCardModels = state.boardCardModelFlow.collectAsLazyPagingItems(),
        onOptionClick = { modelForDialog = it},
        onReplyClick = {}
    )

    BoardOptionDialog(
        model = modelForDialog,
        onDismissRequest = { modelForDialog = null },
        onBoardDelete = viewModel::onBoardDelete
    )
}

@Composable
private fun BoardScreen(
    boardCardModels: LazyPagingItems<BoardCardModel>,
    onOptionClick: (BoardCardModel) -> Unit,
    onReplyClick: (BoardCardModel) -> Unit,
) {
    Surface {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                count = boardCardModels.itemCount,
                key = { index -> boardCardModels[index]?.boardId ?: index }
            ) { index ->
                val boardCardModel = boardCardModels[index]
                boardCardModel?.run {
                    BoardCard(
                        profileImageUrl = null,
                        username = boardCardModel.username,
                        images = boardCardModel.images,
                        text = boardCardModel.text,
                        onOptionClick = { onOptionClick(this) },
                        onReplyClick = { onReplyClick(this) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BoardScreenPreview() {

}