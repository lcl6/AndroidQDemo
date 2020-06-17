package com.example.androidqdemo.view

import android.content.Context
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import com.example.androidqdemo.base.dg.BaseDialog

/**
 * Created by liancl on 2020/6/11 0011.
 */
class PermissionDialog(context: Context) : BaseDialog(context) {
    @JvmField
    @BindView(R.id.tv_content)
    var tvContent: TextView? = null
    @JvmField
    @BindView(R.id.tv_sure)
    var tvSure: TextView? = null
    @JvmField
    @BindView(R.id.tv_cancle)
    var tvCancle: TextView? = null
    private var listener: Listener? = null
    override val layoutId: Int
         get() = R.layout.dg_permission

    override fun findViews() {
        ButterKnife.bind(this)
        tvCancle!!.setOnClickListener { dismiss() }
        tvSure!!.setOnClickListener {
            dismiss()
            if (listener != null) {
                listener!!.onClickSure()
            }
        }
    }

    fun setContent(content: String?) {
        tvContent!!.text = content
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    interface Listener {
        fun onClickSure()
    }
}