package com.lutalic.luboard.model.boards.entities

import java.time.LocalDateTime

typealias Link = String

data class Post(
    val name: String,
    val description: String,
    val dateTime: LocalDateTime,
    val color: ColorsToStateForPosts,
    val linksForFiles: List<Link>? = null
)