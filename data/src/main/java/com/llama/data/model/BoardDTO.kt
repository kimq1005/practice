package com.llama.data.model

import com.llama.domain.model.Board
import kotlinx.serialization.Serializable

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
    return Board(
        id = this.id,
        title = this.title,
        content = this.content,
        images = emptyList(),
        userId = this.createUserId,
        username = this.createUserName,
        profileImageUrl = this.createUserProfileFilePath
    )
}