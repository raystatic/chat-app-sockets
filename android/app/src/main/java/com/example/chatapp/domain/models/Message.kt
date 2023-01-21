package com.example.chatapp.domain.models

data class Message(
    var id: Long = System.currentTimeMillis(),
    val text: String,
    val sentBy: Int,
    val sentTo: Int,
    val timestamp: Long
)
