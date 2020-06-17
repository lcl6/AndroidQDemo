package com.example.androidqdemo.ac

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.tv_test_q, R.id.tv_ts_cj)
    fun onclick(v: View) {
        when (v.id) {
            R.id.tv_test_q -> startActivity(Intent(this@MainActivity, AndroidQActivity::class.java))
            R.id.tv_ts_cj -> StatusActivity.Companion.start(this@MainActivity)
        }
    }
}