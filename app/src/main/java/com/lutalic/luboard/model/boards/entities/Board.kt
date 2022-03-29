package com.lutalic.luboard.model.boards.entities

data class Board(
    val name: String,
    val id: Int,
    val countUsers: Int,
    val priorityForUser: Int // boards sorted by priority
)