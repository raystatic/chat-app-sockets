package com.example.chatapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketHandler {

    private var socket: Socket? = null

    private val _onNewChat = MutableLiveData<Chat>()
    val onNewChat: LiveData<Chat> get() = _onNewChat

    init {
        try {
            socket = IO.socket(SOCKET_URL)
            socket?.connect()

            registerOnNewChat()

        }catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    private fun registerOnNewChat() {
        socket?.on(CHAT_KEYS.BROADCAST) { args->
            args?.let { d ->
               if (d.isNotEmpty()) {
                   val data = d[0]
                   Log.d("DATADEBUG","$data")
                   if (data.toString().isNotEmpty()) {
                       val chat = Gson().fromJson(data.toString(), Chat::class.java)
                       _onNewChat.postValue(chat)
                   }
               }

            }
        }
    }

    fun disconnectSocket() {
        socket?.disconnect()
        socket?.off()
    }


    fun emitChat(chat: Chat) {
        val jsonStr = Gson().toJson(chat, Chat::class.java)
        socket?.emit(CHAT_KEYS.NEW_MESSAGE, jsonStr)
    }

    private object CHAT_KEYS {
        const val NEW_MESSAGE = "new_message"
        const val BROADCAST = "broadcast"
    }

    companion object{
        private const val SOCKET_URL = "http://10.0.2.2:3000/"
    }

}