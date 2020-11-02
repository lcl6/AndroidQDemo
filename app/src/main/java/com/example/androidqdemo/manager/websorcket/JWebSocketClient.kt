package com.example.androidqdemo.manager.websorcket

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

/**
 *Created by liancl on 2020/10/30 0030.
 */

open class JWebSocketClient(serverUri: URI?) : WebSocketClient(serverUri) {

    public var SocketClientTAG:String ="WebSocketClient"
    override fun onOpen(handshakedata: ServerHandshake?) {
        Log.e(SocketClientTAG,"onOpen");
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.e(SocketClientTAG,"onClose: "+reason);
    }

    override fun onMessage(message: String?) {
        Log.e(SocketClientTAG,"onMessage: "+message);
    }

    override fun onError(ex: Exception?) {
        Log.e(SocketClientTAG,"onError: "+ex.toString());
    }
}