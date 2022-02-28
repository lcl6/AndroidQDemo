package com.example.androidqdemo.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.ac.*
import com.example.androidqdemo.base.ac.BaseGreyActivity
import com.example.androidqdemo.base.util.UiHandler
import com.lodz.android.pandora.base.fragment.BaseFragment
import java.io.FileOutputStream
import java.io.InputStream

/**
 *Created by liancl on 2020/11/9 0009.
 */

class MineFragment : BaseFragment() {


    @JvmField
    @BindView(R.id.tv_change)
    var tvChange: TextView? = null

    var  isNight= false;

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
        showStatusLoading()

        UiHandler.postDelayed({
            showStatusCompleted()
        },500)

    }

    @OnClick(R.id.tv_change)
    public fun click(view: View){
        when(view.id){
            R.id.tv_change -> changeAll()

            R.id.tv_change_by_theme -> changeAllByTheme()
        }
    }

    /**
     * 通过更改系统属性
     */
    private fun changeAllByTheme() {

    }


    /**
     * 夜间模式
     */
    private fun changeAll() {
        if(isNight){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            isNight=false
            return
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        isNight=true
    }


    override fun initData(view: View) {
        super.initData(view)
    }

    override fun setListeners(view: View) {
        super.setListeners(view)
    }



}