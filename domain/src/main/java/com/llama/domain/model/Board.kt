package com.llama.domain.model

data class Board(
    val id: Long,
    val title: String,
    val content: String,
    val images: List<String>,
    val userId: Long,
    val username: String,
    val profileImageUrl: String,
)
