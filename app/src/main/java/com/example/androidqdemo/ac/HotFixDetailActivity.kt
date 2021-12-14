package com.example.androidqdemo.ac
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R


/**
 * 事件分发
 * Created by liancl on 2020/6/17 0017.
 */
class HotFixDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot_fix_detail)
        ButterKnife.bind(this)
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, HotFixDetailActivity::class.java)
            context.startActivity(starter)
        }
    }



}


