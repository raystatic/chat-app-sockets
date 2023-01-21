package com.example.chatapp.domain.models

data class User(
    val id: Long = System.currentTimeMillis(),
    val name: String,
    val isTyping: Boolean = false,
)
