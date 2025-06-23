package com.llama.data.usecase.main.setting

import com.llama.domain.model.Image
import com.llama.domain.usecase.file.GetImageUseCase
import com.llama.domain.usecase.file.UploadImageUseCase
import com.llama.domain.usecase.main.setting.GetMyUserUseCase
import com.llama.domain.usecase.main.setting.SetMyUserUseCase
import com.llama.domain.usecase.main.setting.SetProfileImageUseCase
import javax.inject.Inject

class SetProfileImageUseCaseImpl @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val setMyUserUseCase: SetMyUserUseCase,
    private val getMyUserUseCase: GetMyUserUseCase,
): SetProfileImageUseCase {
    override suspend fun invoke(contentUri: String): Result<Unit> = runCatching {
        // 0: 내 정보 가져오기
        val user = getMyUserUseCase().getOrThrow()

        // 1: 이미지 정보 가져오기
        val image: Image = getImageUseCase(
            contentUri = contentUri
        ) ?: throw IllegalArgumentException("이미지가 존재하지 않습니다.")

        // 2. 이미지 업로드 하기
        val imagePath = uploadImageUseCase(image).getOrThrow()

        // 3. 내 정보 업데이트 하기
        setMyUserUseCase(
            profileImageUrl = imagePath
        ).getOrThrow()
    }
}