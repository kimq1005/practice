package com.llama.data.retrofit

import com.llama.data.model.CommonResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("users/login")
    suspend fun login(
        @Body requestBody: RequestBody,
    ): CommonResponse<String>
}