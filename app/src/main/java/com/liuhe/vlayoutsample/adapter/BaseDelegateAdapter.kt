package com.liuhe.vlayoutsample.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.util.Linkify
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.*
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper

/**
 * v-layout框架适配器基类
 * @author liuhe
 * @date 2018/3/6
 */
open class BaseDelegateAdapter(private val context: Context, private val layoutHelper: LayoutHelper, private val layoutId: Int, private val count: Int, val viewTypeItem: Int) : DelegateAdapter.Adapter<BaseViewHolder>() {

    override fun onCreateLayoutHelper(): LayoutHelper = layoutHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder? {
        return if (viewType == viewTypeItem) {
            BaseViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false))
        } else {
            null
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {}

    /**
     * 必须重写不然会出现滑动不流畅的情况
     */
    override fun getItemViewType(position: Int): Int = viewTypeItem

    /**
     * 条目数量
     * @return          条目数量
     */
    override fun getItemCount(): Int = count
}

/**
 * v-layout框架ViewHolder基类
 * @author liuhe
 * @date 2018/3/6
 */
class BaseViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    /**
     * SparseArray 比 HashMap 更省内存，在某些条件下性能更好，只能存储 key 为 int 类型的数据，
     * 用来存放 View 以减少 findViewById 的次数
     */
    private val viewSparseArray: SparseArray<View> = SparseArray()

    /**
     * 根据 ID 来获取 View
     * @param viewId viewID
     * @param <T>    泛型
     * @return 将结果强转为 View 或 View 的子类型
     * */
    fun <T : View> getView(viewId: Int): T? {
        /**
         * 先从缓存中找，找到的话则直接返回
         * 如果找不到则 findViewById ，再把结果存入缓存中
         */
        var view: View? = viewSparseArray.get(viewId)
        if (view == null) {
            view = itemView?.findViewById(viewId)
            viewSparseArray.put(viewId, view)
        }
        return view as T?
    }


    /**
     * 设置TextView的值
     */
    fun setText(viewId: Int, text: String): BaseViewHolder {
        val tv = getView<TextView>(viewId)
        tv?.text = text
        return this
    }

    /**
     * 设置imageView图片
     */
    fun setImageResource(viewId: Int, resId: Int): BaseViewHolder {
        val view = getView<ImageView>(viewId)
        view?.setImageResource(resId)
        return this
    }

    /**
     * 设置imageView图片
     */
    fun setImageBitmap(viewId: Int, bitmap: Bitmap): BaseViewHolder {
        val view = getView<ImageView>(viewId)
        view?.setImageBitmap(bitmap)
        return this
    }

    /**
     * 设置imageView图片
     */
    fun setImageDrawable(viewId: Int, drawable: Drawable): BaseViewHolder {
        val view = getView<ImageView>(viewId)
        view?.setImageDrawable(drawable)
        return this
    }

    /**
     * 设置背景颜色
     */
    fun setBackgroundColor(viewId: Int, color: Int): BaseViewHolder {
        val view = getView<View>(viewId)
        view?.setBackgroundColor(color)
        return this
    }

    /**
     * 设置背景颜色
     */
    fun setBackgroundRes(viewId: Int, backgroundRes: Int): BaseViewHolder {
        val view = getView<View>(viewId)
        view?.setBackgroundResource(backgroundRes)
        return this
    }

    /**
     * 设置text颜色
     */
    fun setTextColor(viewId: Int, textColor: Int): BaseViewHolder {
        val view = getView<TextView>(viewId)
        view?.setTextColor(textColor)
        return this
    }

    /**
     * 设置透明度
     */
    @SuppressLint("NewApi")
    fun setAlpha(viewId: Int, value: Float): BaseViewHolder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView<View>(viewId)?.alpha = value
        } else {
            // Pre-honeycomb hack to set Alpha value
            val alpha = AlphaAnimation(value, value)
            alpha.duration = 0
            alpha.fillAfter = true
            getView<View>(viewId)?.startAnimation(alpha)
        }
        return this
    }


    /**
     * 设置是否可见
     */
    fun setVisible(viewId: Int, visible: Boolean): BaseViewHolder {
        val view = getView<View>(viewId)
        view?.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }


    fun linkify(viewId: Int): BaseViewHolder {
        val view = getView<TextView>(viewId)
        Linkify.addLinks(view, Linkify.ALL)
        return this
    }


    fun setProgress(viewId: Int, progress: Int): BaseViewHolder {
        val view = getView<ProgressBar>(viewId)
        view?.progress = progress
        return this
    }

    fun setProgress(viewId: Int, progress: Int, max: Int): BaseViewHolder {
        val view = getView<ProgressBar>(viewId)
        view?.max = max
        view?.progress = progress
        return this
    }

    fun setMax(viewId: Int, max: Int): BaseViewHolder {
        val view = getView<ProgressBar>(viewId)
        view?.max = max
        return this
    }

    fun setRating(viewId: Int, rating: Float): BaseViewHolder {
        val view = getView<RatingBar>(viewId)
        view?.rating = rating
        return this
    }

    fun setRating(viewId: Int, rating: Float, max: Int): BaseViewHolder {
        val view = getView<RatingBar>(viewId)
        view?.max = max
        view?.rating = rating
        return this
    }

    fun setTag(viewId: Int, tag: Any): BaseViewHolder {
        val view = getView<View>(viewId)
        view?.tag = tag
        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any): BaseViewHolder {
        val view = getView<View>(viewId)
        view?.setTag(key, tag)
        return this
    }

    fun setChecked(viewId: Int, checked: Boolean): BaseViewHolder {
        val view = getView<View>(viewId) as Checkable
        view.isChecked = checked
        return this
    }

    /**
     * 关于事件的
     */
    fun setOnClickListener(viewId: Int, listener: View.OnClickListener): BaseViewHolder {
        val view = getView<View>(viewId)
        view?.setOnClickListener(listener)
        return this
    }

    fun setOnTouchListener(viewId: Int, listener: View.OnTouchListener): BaseViewHolder {
        val view = getView<View>(viewId)
        view?.setOnTouchListener(listener)
        return this
    }

    fun setOnLongClickListener(viewId: Int, listener: View.OnLongClickListener): BaseViewHolder {
        val view = getView<View>(viewId)
        view?.setOnLongClickListener(listener)
        return this
    }
}
