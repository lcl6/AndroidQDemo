package com.example.andr


import android.content.Context
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import com.example.androidqdemo.adapter.DownLoadPicAdapter
import com.example.androidqdemo.base.adapter.BaseRecyclerViewAdapter
import com.example.androidqdemo.bean.MeiziDetailBean

/**
 * Created by liancl on 2020/6/11 0011.
 */
 class EditextAdapter(context: Context?) : BaseRecyclerViewAdapter<String?>(context) {


    override fun onBind(holder: ViewHolder, position: Int) {
        getItem(position).let {
            if (it != null) {
                setItem(holder as Viewholder, it,position)
            }
        }
    }

    private fun setItem(holder: Viewholder,  item: String ,position: Int) {

        holder.edBottom?.text= Editable.Factory.getInstance().newEditable(item)
        holder.edBottom?.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> }
        holder.edBottom?.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(holder.adapterPosition<0){
                    return
                }
                data[holder.adapterPosition]=s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

//        //如果是新增的 需要 键盘收起不要
//        if(position==holder.adapterPosition){
//            holder.edBottom?.isFocusable = true
//            holder.edBottom?.isFocusableInTouchMode = true
//            holder.edBottom?.requestFocus()
//            holder.edBottom?.isCursorVisible = true
//            Selection.setSelection(holder.edBottom?.text, holder.edBottom?.text.toString().length)
//        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return Viewholder(getLayoutView(viewGroup, R.layout.it_ed_item))
    }

     class Viewholder(itemView: View) : ViewHolder(itemView) {
        @JvmField
        @BindView(R.id.it_bottom)
        var edBottom: EditText? = null
        init {
            ButterKnife.bind(this, itemView)
        }
    }


    public interface Listener{
        fun click(item: MeiziDetailBean,position: Int)
    }

    public fun DownLoadPicAdapter.setListener(li: DownLoadPicAdapter.Listener){
        this.listener=li
    }
}
