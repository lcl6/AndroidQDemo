package com.example.androidqdemo.ac

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Selection
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.andr.EditextAdapter
import com.example.androidqdemo.R
import com.example.androidqdemo.util.KeyBoardUtil
import java.util.*

/**
 * editext 列表问题
 */
class EditextRecyclerViewPicActivity : AppCompatActivity() {
    @JvmField
    @BindView(R.id.recycler_view)
    var mRecyclerView: RecyclerView? = null
    @JvmField
    @BindView(R.id.vg_loadmore)
    var vgLoad: ViewGroup? = null

    var mList: MutableList<String>? = null
    var mAdapter: EditextAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edtext_rv)
        ButterKnife.bind(this)
        initRecyclerView()
        queryData()
    }

    private fun queryData() {
        for (i in 1..20){
            mList?.add("测试$i")
        }
        mAdapter?.notifyDataSetChanged()
    }

    @ExperimentalStdlibApi
    @OnClick(R.id.tv_add,R.id.tv_delete)
    fun click(v :View){
        when(v.id){
            R.id.tv_add->addItem()
            R.id.tv_delete->deleteItem()
        }
    }

    @ExperimentalStdlibApi
    private fun deleteItem() {
        mAdapter?.notifyItemRemovedChanged(mList!!.size-1)
    }

    private fun addItem() {

        var text:String ="添加数据"
        mList?.add(text)
//        mAdapter?.notifyItemInserted(mList!!.size-1)
        mRecyclerView?.scrollToPosition(mList!!.size-1)
//        mRecyclerView?.let { KeyBoardUtil.showInputMethod(it) }
        mAdapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        mList = ArrayList()
        mRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mAdapter = EditextAdapter(this)
        mRecyclerView!!.adapter = mAdapter
        mAdapter!!.data = mList as List<String?>?
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, EditextRecyclerViewPicActivity::class.java)
            context.startActivity(starter)
        }
    }

}