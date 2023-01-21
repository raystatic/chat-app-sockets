package com.example.chatapp.data

import android.util.Log
import com.example.chatapp.domain.models.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ChatRepository {

    private val socket = SocketHandler.getSocket()


//    suspend fun createConnection() = withContext(scope){
//        socket?.connect()
//    }

    fun sendMessage(message: Message){
        socket?.send("SEND_NEW_MESSAGE", "{text:${message.text}}")
    }

    fun readMessage(userId: Int) {
        socket?.on("RECEIVE_NEW_MESSAGE"){args->
            Log.d("Received ARGS", "$args")
        }
    }

    suspend fun closeConnection() = withContext(Dispatchers.IO){
        socket?.disconnect()
    }

    companion object{
        private var chatRepository: ChatRepository? = null
        val scope = Dispatchers.IO
        fun getChatRepository(): ChatRepository{
            return if (chatRepository == null) {
                chatRepository = ChatRepository()
                chatRepository!!
            }else {
                chatRepository!!
            }
        }
    }


}