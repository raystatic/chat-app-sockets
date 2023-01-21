package com.example.chatapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class Chat(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val username: String,
    val text: String,
    var isSelf: Boolean = false
)