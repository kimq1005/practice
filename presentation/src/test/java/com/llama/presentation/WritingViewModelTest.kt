package com.llama.presentation

import com.llama.domain.model.Image
import com.llama.domain.usecase.main.writing.GetImageListUseCase
import com.llama.domain.usecase.main.writing.PostBoardUseCase
import com.llama.presentation.main.writing.WritingSideEffect
import com.llama.presentation.main.writing.WritingViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

private val testImage = Image(
    uri = "testUri",
    name = "llama",
    size = 1,
    mimeType = "meTest"
)

class WritingViewModelTest {
    private lateinit var getImageListUseCase: FakeGetImageListUseCase
    private lateinit var postBoardUseCase: FakePostBoardUseCase
    private lateinit var viewModel: WritingViewModel

    @Before
    fun setUp() {
        getImageListUseCase = FakeGetImageListUseCase()
        postBoardUseCase = FakePostBoardUseCase()
        viewModel = WritingViewModel(
            getImageListUseCase,
            postBoardUseCase
        )
    }

    @Test
    fun `이미지 아이템 클릭 테스트_똑같은 이미지가 없을때`() = runTest {
        for (i in 0..3) {
            viewModel.onItemClick(
                testImage.copy(
                    uri = "testUri$i",
                )
            )
        }

        val selectedImages = viewModel.container.stateFlow.map {
            it.selectedImage
        }

        Assert.assertEquals(selectedImages.first().size, 4)
    }

    @Test
    fun `이미지 아이템 클릭 테스트_똑같은 이미지가 있을때`() = runTest {
        for (i in 0..3) {
            viewModel.onItemClick(testImage)
        }

        val selectedImages = viewModel.container.stateFlow.map {
            it.selectedImage
        }

        Assert.assertEquals(selectedImages.first().size, 0)
    }

    @Test
    fun `게시 클릭 이벤트 테스트` () = runTest {
        viewModel.onPostClick()
        val sideEffect = viewModel.container.sideEffectFlow.first()
        Assert.assertTrue(sideEffect is WritingSideEffect.Finish)

    }

    class FakeGetImageListUseCase : GetImageListUseCase {
        override suspend fun invoke(): List<Image> {
            return emptyList()
        }
    }

    class FakePostBoardUseCase: PostBoardUseCase {
        override suspend fun invoke(
            title: String,
            content: String,
            images: List<Image>
        ) {

        }
    }
}