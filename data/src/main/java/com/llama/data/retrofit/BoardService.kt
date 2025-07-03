package com.llama.data.retrofit

import com.llama.data.model.BoardDTO
import com.llama.data.model.CommonResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardService {
    @GET("boards")
    suspend fun getBoards(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): CommonResponse<List<BoardDTO>>

    @POST("boards")
    suspend fun postBoard(
        @Body requestBody: RequestBody,
    ): CommonResponse<Long>

    @DELETE("boards/{id}")
    suspend fun deleteBoard(
        @Path("id") id: Long,
    ): CommonResponse<Long>
}