package com.example.androidqdemo.fragment

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.base.util.ToastUtils
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.event.BaseEvent
import com.example.androidqdemo.util.LogicUtils
import com.lodz.android.pandora.base.fragment.BaseFragment
import org.greenrobot.eventbus.EventBus

/**
 *Created by liancl on 2020/11/9 0009.
 */

class MineFragment : BaseThemeFragment() {


    @JvmField
    @BindView(R.id.tv_change)
    var tvChange: TextView? = null

    var  isNight= false;
    lateinit var mView:View;

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
        this.mView=view;
        ButterKnife.bind(this,view)
        showStatusLoading()

        UiHandler.postDelayed({
            showStatusCompleted()
        },500)

    }

    @OnClick(R.id.tv_change,R.id.tv_change_by_theme,R.id.tv_change_icon,R.id.tv_back_icon)
    public fun click(view: View){
        when(view.id){
            R.id.tv_change -> changeAll()
            R.id.tv_change_by_theme -> changeAllByTheme(view)
            R.id.tv_change_icon -> changeAppIcon()
            R.id.tv_back_icon -> setDefaultAppIcon()
        }
    }

    //设置其他图标
    private fun changeAppIcon() {

        val packageManager = activity?.packageManager
        activity?.let {
            val componentName = ComponentName(it, "com.example.androidqdemo.TestActivity")
            packageManager?.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP)
            val componentName1 = ComponentName(it, "com.example.androidqdemo.DefaultActivity")
            packageManager?.setComponentEnabledSetting(componentName1,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP)
            ToastUtils.show(it,"切换图标中");
        }
    }

    //设置默认图标
    private fun setDefaultAppIcon() {
        val packageManager = activity?.packageManager
        activity?.let {
            val componentName = ComponentName(it, "com.example.androidqdemo.DefaultActivity")
            packageManager?.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP)
            val componentName1 = ComponentName(it, "com.example.androidqdemo.TestActivity")
            packageManager?.setComponentEnabledSetting(componentName1,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP)
            ToastUtils.show(it,"切换图标中");
        }

    }

    /**
     * 通过更改系统属性
     */
    private fun changeAllByTheme(view: View) {
        changeTheme();
        EventBus.getDefault().post(BaseEvent());
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