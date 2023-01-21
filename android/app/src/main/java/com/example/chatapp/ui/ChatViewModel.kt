package com.example.chatapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.ChatRepository
import com.example.chatapp.domain.models.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModel: ViewModel() {

    private val chatRepository = ChatRepository.getChatRepository()

    fun sendMessage(source: Int, destination: Int, text:String) = viewModelScope.launch{
        val message: Message = Message(
            text = text,
            sentBy = source,
            sentTo = destination,
            timestamp = System.currentTimeMillis()
        )
        chatRepository.sendMessage(message = message)
    }

}