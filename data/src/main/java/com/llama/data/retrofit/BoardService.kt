package com.llama.data.retrofit

import com.llama.data.model.CommonResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface BoardService {
    @POST("boards")
    fun postBoard(
        @Body requestBody: RequestBody,
    ): CommonResponse<Long>
}