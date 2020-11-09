package com.example.androidqdemo.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.ac.*
import com.lodz.android.pandora.base.fragment.BaseFragment
import java.io.FileOutputStream
import java.io.InputStream

/**
 *Created by liancl on 2020/11/9 0009.
 */

class MineFragment : BaseFragment() {

    var change:Boolean = false;

    companion object{
        fun  getInstance():MineFragment{
            val homeFragment = MineFragment()
            return homeFragment;
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fg_mine
    }


    override fun findViews(view: View, savedInstanceState: Bundle?) {
        super.findViews(view, savedInstanceState)
        ButterKnife.bind(this,view)
        showStatusCompleted()
    }

    @OnClick(R.id.tv_change)
    public fun click(view: View){
        when(view.id){
            R.id.tv_change -> changeAll()
        }
    }

    /**
     * 换肤
     */
    private fun changeAll() {
        change=!change;

    }


    override fun initData(view: View) {
        super.initData(view)
    }

    override fun setListeners(view: View) {
        super.setListeners(view)
    }



}