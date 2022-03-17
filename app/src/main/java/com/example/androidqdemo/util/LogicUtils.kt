package com.example.androidqdemo.util

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import com.example.androidqdemo.R

/**
 *Created by  on 2022/3/17.
 */

class LogicUtils {
    companion object{
        fun  findChildView( context: Context,rootView: ViewGroup){
            val childCount = rootView.childCount
            for (i in 0 until childCount){
                val childView  = rootView.getChildAt(i)
                if(childView is ViewGroup) {
                    childView.setBackgroundColor(context.resources.getColor(R.color.black_color))
                    findChildView(context, childView);
                }else if(childView is TextView) {
                    childView.setTextColor(context.resources.getColor(R.color.white))
                }

            }
        }
    }

}