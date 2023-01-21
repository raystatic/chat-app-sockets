package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.chatapp.data.SocketHandler
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.domain.models.Message
import com.example.chatapp.ui.ChatViewModel
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewmodel by viewModels<ChatViewModel>()

    private lateinit var socket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        try {
//            //This address is the way you can connect to localhost with AVD(Android Virtual Device)
//            socket = IO.socket("http://192.168.0.193:3000")
//            initSocket()
//            Log.d("SOCKETDebug", "$socket")
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Log.d("SOCKETDebug", "Failed to connect")
//        }
//
//        binding.text.setOnClickListener {
//            Log.d("SOCKETDebug","isconnected: ${socket.connected()}")
//            socket.emit("abc")
//        }
//
//        socket.on("def"){
//            Log.d("SOCKETDebug","def ${it[0]}")
//        }

    }

    private fun initSocket() {

        socket.on(Socket.EVENT_CONNECT
        ) {
            Log.d("SOCKETDebug", "EVENT connect")
            Log.d("SOCKETDebug", "socket id: ${socket.connect().id()}")
        }.on(Socket.EVENT_CONNECT_ERROR){
            Log.d("SOCKETDebug", "EVENT connect error: ${it[0]}")
        }.on(Socket.EVENT_DISCONNECT){
            Log.d("SOCKETDebug", "EVENT disconnect: $it")
        }

        socket.connect()
    }

    override fun onResume() {
        super.onResume()
        viewmodel.sendMessage(1,2,"Hi")
//        SocketHandler.setSocket()
//        SocketHandler.establishConnection()
//        SocketHandler.getSocket().emit("SEND_NEW_MESSAGE",Message(
//            text = "Hi",
//            sentTo = 1,
//            sentBy = 2,
//            timestamp = System.currentTimeMillis()
//        ))

//        socket.connect()

//        socket.emit("abc")

    }
}