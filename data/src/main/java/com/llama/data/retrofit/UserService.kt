package com.llama.data.retrofit

import com.llama.data.model.CommonResponse
import com.llama.data.model.UserDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {
    @POST("users/login")
    suspend fun login(
        @Body requestBody: RequestBody,
    ): CommonResponse<String>

    @POST("users/sign-up")
    suspend fun signUp(
        @Body requestBody: RequestBody,
    ): CommonResponse<Int>

    @GET("users/my-page")
    suspend fun myPage(): CommonResponse<UserDTO>

    @PATCH("users/my-page")
    suspend fun pathMyPage(
        @Body requestBody: RequestBody,
    ): CommonResponse<Long>
}