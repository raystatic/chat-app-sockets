package com.example.chatapp.data

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    private const val SERVER_URL = "http://10.0.2.2:3000/"

    private var socket: Socket?=null

    @Synchronized
    fun setSocket(){
        try {
            socket = IO.socket(SERVER_URL)
        }catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    @Synchronized
    fun getSocket(): Socket? {
        return if (socket == null){
            try {
                socket = IO.socket(SERVER_URL)
                socket!!.connect()
                socket!!
            }catch (e: URISyntaxException) {
                e.printStackTrace()
                null
            }
        }else {
            null
        }
    }

    @Synchronized
    fun establishConnection() {
        socket?.connect()
    }

    @Synchronized
    fun closeConnection() {
        socket?.disconnect()
    }

}