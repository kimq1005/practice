package com.llama.data.usecase.file

import com.llama.data.di.Llama_Host
import com.llama.data.retrofit.FileService
import com.llama.data.retrofit.UriRequestBody
import com.llama.domain.model.Image
import com.llama.domain.usecase.file.GetInputStreamUseCase
import com.llama.domain.usecase.file.UploadImageUseCase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadImageUseCaseImpl @Inject constructor(
    private val fileService: FileService,
    private val getInputStreamUseCase: GetInputStreamUseCase
): UploadImageUseCase {
    override suspend fun invoke(image: Image): Result<String>  = runCatching {
        val fileNamePart = MultipartBody.Part.createFormData(
            name = "fileName",
            image.name
        )

        val requestBody = UriRequestBody(
            contentUri = image.uri,
            getInputStreamUseCase = getInputStreamUseCase,
            contentType = image.mimeType.toMediaType(),
            contentLength = image.size
        )

        val filePart = MultipartBody.Part.createFormData(
            "file",
            image.name,
            requestBody
        )

        Llama_Host + fileService.uploadImage(
            fileName = fileNamePart,
            file = filePart
        ).data.filePath
    }
}