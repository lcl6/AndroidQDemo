package com.example.androidqdemo.ac

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import java.io.FileOutputStream
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.tv_test_q, R.id.tv_ts_cj,R.id.tv_test_saf,R.id.tv_ts_cache,R.id.tv_down_pic)
    fun onclick(v: View) {
        when (v.id) {
            R.id.tv_test_q -> startActivity(Intent(this@MainActivity, AndroidQActivity::class.java))
            R.id.tv_ts_cj -> StatusActivity.Companion.start(this@MainActivity)
            R.id.tv_test_saf -> showFileChooser()
            R.id.tv_ts_cache ->CacheActivity.start(this@MainActivity)
            R.id.tv_down_pic ->DownLoadPicActivity.start(this@MainActivity)
        }
    }

    private fun showFileChooser() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        // 只显示可以打开的文件
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        // 可选择所有文件类型
        intent.type = "*/*"
        // 只可选择jpeg图片
        // intent.type = "image/jpeg"
        startActivityForResult(intent, 1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            data?.data?.let {
                //copy 一份到本地文件夹
                val inputStream = contentResolver.openInputStream(it)
                if (inputStream != null) {
                    writeToLocal(externalCacheDir?.path+System.currentTimeMillis()+".db",inputStream)
                };
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun writeToLocal(destination: String?, input: InputStream) {
        var index: Int
        val bytes = ByteArray(1024)
        val downloadFile = FileOutputStream(destination)
        while (input.read(bytes).also { index = it } != -1) {
            downloadFile.run {
                write(bytes, 0, index)
                flush()
            }
        }
        input.close()
        downloadFile.close()
    }

}