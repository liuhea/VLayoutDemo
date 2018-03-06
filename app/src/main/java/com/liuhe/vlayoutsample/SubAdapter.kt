package com.liuhe.vlayoutsample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper

/**
 * @author liuhe
 * @date 2018/3/6
 */
class SubAdapter(private val mContext: Context, private val mLayoutHelper: LayoutHelper, private val mCount: Int) : DelegateAdapter.Adapter<SubAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rcy, parent, false))
    }

    override fun getItemCount(): Int = mCount

    override fun onCreateLayoutHelper(): LayoutHelper = mLayoutHelper

    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
