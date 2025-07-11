package com.llama.presentation.main.writing

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.llama.domain.model.Image
import com.llama.domain.usecase.main.writing.GetImageListUseCase
import com.llama.domain.usecase.main.writing.PostBoardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WritingViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase,
    private val postBoardUseCase: PostBoardUseCase,
) : ViewModel(), ContainerHost<WritingState, WritingSideEffect> {
    override val container: Container<WritingState, WritingSideEffect> = container(
        initialState = WritingState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent { postSideEffect(WritingSideEffect.Toast(throwable.message.orEmpty())) }
            }
        }
    )

    init {
        load()
    }

    private fun load() = intent {
        val images = getImageListUseCase()

        reduce {
            state.copy(
                selectedImage = images.firstOrNull()?.let { listOf(it) } ?: emptyList(),
                images = images
            )
        }
    }

    fun onItemClick(image: Image) = intent {
        reduce {
            if (state.selectedImage.contains(image)) {
                state.copy(
                    selectedImage = state.selectedImage - image
                )
            } else {
                state.copy(
                    selectedImage = state.selectedImage + image
                )
            }
        }
    }

    fun onPostClick() = intent {
        postBoardUseCase(
            title = "테스트 제목입니다.",
            content = state.text,
            images = state.selectedImage
        )

        postSideEffect(WritingSideEffect.Finish)
    }

    fun onTextChange(text: String) = blockingIntent {
        reduce {
            state.copy(text = text)
        }
    }
}

@Immutable
data class WritingState(
    val selectedImage: List<Image> = emptyList(),
    val images: List<Image> = emptyList(),
    val text: String = ""
)

sealed interface WritingSideEffect {
    class Toast(val message: String) : WritingSideEffect
    object Finish: WritingSideEffect
}