package com.llama.data.model

import com.llama.domain.model.Board
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class BoardDTO(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val createUserId: Long,
    val createUserName: String,
    val createUserProfileFilePath: String
)

fun BoardDTO.toDomain(): Board {
    val contentParam = Json.decodeFromString<ContentParam>(content)
    return Board(
        id = this.id,
        title = this.title,
        content = contentParam.text,
        images = contentParam.images,
        userId = this.createUserId,
        username = this.createUserName,
        profileImageUrl = this.createUserProfileFilePath
    )
}