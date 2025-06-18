package com.llama.data.retrofit

import android.util.Log
import com.llama.domain.usecase.file.GetInputStreamUseCase
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source

class UriRequestBody constructor(
    val contentUri: String,
    val getInputStreamUseCase: GetInputStreamUseCase,
    val contentType: MediaType? = null,
    val contentLength: Long
) : RequestBody() {
    override fun contentType(): MediaType? {
        return contentType
    }

    override fun contentLength(): Long {
        return contentLength
    }

    override fun writeTo(sink: BufferedSink) {
        try {
            getInputStreamUseCase(
                contentUri = contentUri
            ).getOrThrow()
                .use { inputStrem ->
                    sink.writeAll(inputStrem.source())
                }
        } catch (e: Exception) {
            Log.e("UriRequestBody", "${e.message}")
        }
    }
}