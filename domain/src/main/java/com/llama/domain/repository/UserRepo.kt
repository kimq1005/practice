package com.llama.domain.repository

import okhttp3.RequestBody

interface UserRepo {
    suspend fun login(
        requestBody: RequestBody,
    ): String

    suspend fun signUp(
        requestBody: RequestBody,
    ): Int

    suspend fun myPage(): UserRepo

    suspend fun pathMyPage(
       requestBody: RequestBody,
    ):Long
}