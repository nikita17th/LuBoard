package com.lutalic.luboard.model.boards.entities

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

typealias BoardId = Int // FIXME if necessary

data class Board(
    val name: String,
    val description: String? = null, // optional field
    val id: Int,
    val countUsers: Int,
    var postList: Flow<List<Post>>
)