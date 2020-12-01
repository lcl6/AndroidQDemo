package com.example.androidqdemo.dl

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.util.ScreenUtils
import com.lodz.android.pandora.widget.dialog.BaseCenterDialog

/**
 *Created by liancl on 2020/11/9 0009.
 */

class TipsDialog(context: Context) : BaseCenterDialog(context) {

    private lateinit var listener: Listener

    var titleString:String=""
    @JvmField
    @BindView(R.id.tv_tips)
     var tvContent:TextView?=null

    override fun getLayoutId(): Int {
        return R.layout.dl_tips
    }

    override fun findViews() {
        super.findViews()
        val dm = DisplayMetrics()
        window?.windowManager?.defaultDisplay?.getMetrics(dm)
        val win = window
        val params = win?.attributes
        // Dialog 宽度充满整个屏幕
        // Dialog 宽度充满整个屏幕
        params!!.width = ((ScreenUtils.getScreenWidth(context) * 0.8).toInt())
        win!!.attributes = params
        ButterKnife.bind(this)
    }

    override fun initData() {
        super.initData()
        tvContent!!.text=titleString
    }

    @OnClick(R.id.tv_sure)
    public fun onClick(view :View){
        when(view.id){
            R.id.tv_sure->listener.onClicSure()
        }

    }

    fun setListener(l:Listener){
        this.listener=l
    }
    interface Listener{
       fun onClicSure()
    }

    fun setTitle(title:String){
        titleString=title
//        tvContent!!.text=title
    }




}