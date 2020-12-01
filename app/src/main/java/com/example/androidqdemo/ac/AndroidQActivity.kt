package com.example.androidqdemo.ac

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import com.example.androidqdemo.adapter.FileAdapter
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.bean.FileEntity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class AndroidQActivity : AppCompatActivity() {
    @JvmField
    @BindView(R.id.recycler_view)
    var mRecyclerView: RecyclerView? = null
    var mList: List<FileEntity>? = null
    var fileAdapter: FileAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_q)
        ButterKnife.bind(this)
        initRecyclerView()
        Thread(Runnable {
            mList = getFilesByType(this@AndroidQActivity)
            UiHandler.post {
                fileAdapter?.data = mList
                fileAdapter!!.notifyDataSetChanged()
            }
        }).start()
    }

    private fun initRecyclerView() {
        mList = ArrayList()
        mRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        fileAdapter = FileAdapter(this)
        mRecyclerView!!.adapter = fileAdapter
        fileAdapter!!.data = mList
    }

    /**
     * 获取所有文件
     */
    fun getFilesByType(context: Context): List<FileEntity> {
        val files: MutableList<FileEntity> = ArrayList()
        // 扫描files文件库
        var c: Cursor? = null
        try {
            var mContentResolver = context.contentResolver
            var select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.db'" + " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.db'" + ")"

             c = mContentResolver.query(MediaStore.Files.getContentUri("external"), null, select, null, null);
            val columnIndexOrThrow_ID = c!!.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val columnIndexOrThrow_MIME_TYPE = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
            val columnIndexOrThrow_DATA = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
            val columnIndexOrThrow_SIZE = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
            // 更改时间
            val columnIndexOrThrow_DATE_MODIFIED = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)
            var tempId = 0
            while (c.moveToNext()) {
                val path = c.getString(columnIndexOrThrow_DATA)
                val minType = c.getString(columnIndexOrThrow_MIME_TYPE)
                Log.d("FileManager", "path:$path")
                val position_do = path.lastIndexOf(".")
                if (position_do == -1) {
                    continue
                }
                val position_x = path.lastIndexOf(File.separator)
                if (position_x == -1) {
                    continue
                }
                val displayName = path.substring(position_x + 1, path.length)
                val size = c.getLong(columnIndexOrThrow_SIZE)
                val modified_date = c.getLong(columnIndexOrThrow_DATE_MODIFIED)
                val file = File(path)
                val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(file.lastModified()))
                val info = FileEntity()
                info.name = displayName
                info.path = path
                info.size = size.toString() + ""
                info.id = tempId++.toString() + ""
                info.time = time
                files.add(info)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            c?.close()
        }
        return files
    }

    companion object {
        /**
         * 构造文件的查询字段
         *
         * @return 文件查询的字段
         */
        private fun buildOfficeSelectionStr(): String {
            return "(" +  //Excel
                    MediaStore.Files.FileColumns.DATA + " LIKE '%.xls'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.xlsx'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.xlsm'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.xltx'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.xltm'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.xlam'" +  // PowerPoint
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.pptx'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.pptm'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.potm'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.potx'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.ppsx'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.ppsm'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.sldx'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.thmx'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.ppt'" +  // Word
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.dotm'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.docx'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.docm'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.dotx'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.doc'" +  // Project
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.mpp'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.zip'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.mp3'" +  // Visio
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.vsd'" +  // Access
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.mdb'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.mde'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.accdb'" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.rar'" + ")"
        }
    }
}