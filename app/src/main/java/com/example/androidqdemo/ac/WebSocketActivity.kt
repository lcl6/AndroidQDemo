package com.example.androidqdemo.ac

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.manager.websorcket.JWebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI


/**
 * 测试websocket
 * Created by liancl on 2020/6/17 0017.
 */
class WebSocketActivity : AppCompatActivity() {
    @JvmField
    @BindView(R.id.ed_wen)
    var edWen: EditText? = null

    @JvmField
    @BindView(R.id.tv_da)
    var tvDa: TextView? = null

    @JvmField
    @BindView(R.id.tv_connect)
    var tvConnect: TextView? = null

    @JvmField
    @BindView(R.id.tv_stop)
    var tvStop: TextView? = null

    @JvmField
    @BindView(R.id.tv_send)
    var tvSend: TextView? = null



    var client: JWebSocketClient?=null;
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_websocket)
        ButterKnife.bind(this)


        val uri: URI = URI.create("ws://121.40.165.18:8800")
        client= object : JWebSocketClient(uri) {
            override fun onMessage(message: String?) {
                Log.e(SocketClientTAG, message)
                UiHandler.post {
                    tvDa!!.text="onMessage: "+message;
                }

            }

            override fun onOpen(handshakedata: ServerHandshake?) {
                super.onOpen(handshakedata)
                UiHandler.post {
                    tvDa!!.text="onOpen: 打开";
                }


            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                super.onClose(code, reason, remote)
                UiHandler.post {
                    tvDa!!.text="onClose: "+reason;
                }

            }

            override fun onError(ex: java.lang.Exception?) {
                super.onError(ex)
                UiHandler.post {
                    tvDa!!.text="onError: "+ex.toString();
                }

            }

        }


        tvConnect?.setOnClickListener {
            connect()


        }

        tvStop?.setOnClickListener {
            stop()
        }

        tvSend?.setOnClickListener {

            send();

        }
    }

    private fun send() {
        if (client != null && client!!.isOpen()) {
            client!!.send(edWen?.text.toString());
        }
    }

    private fun stop() {
        try {
            if (null != client) {
                client!!.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            client = null
        }

    }

    private fun connect() {
        try {
            client?.connectBlocking()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }


    companion object {
        fun start(context: Context) {
            val starter = Intent(context, WebSocketActivity::class.java)
            context.startActivity(starter)
        }
    }
}