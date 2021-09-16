package com.example.androidqdemo.ac

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidqdemo.R

/**
 *Created by liancl on 2021/9/14.
 */

 class AspActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asp)
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, AspActivity::class.java)
            context.startActivity(starter)
        }
    }
}