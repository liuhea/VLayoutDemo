package com.liuhe.vlayoutsample

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper
import com.liuhe.vlayoutsample.adapter.BaseDelegateAdapter
import com.liuhe.vlayoutsample.adapter.BaseViewHolder
import com.liuhe.vlayoutsample.ui.SecondActivity
import com.liuhe.vlayoutsample.utils.NetCallBack
import com.liuhe.vlayoutsample.utils.getHomeData
import com.liuhe.vlayoutsample.utils.loadImage
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import java.util.*


class MainActivity : AppCompatActivity() {

    var homeData: HomeData? = null
    val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testData()

        btn_main_send.setOnClickListener({
            EventBus.getDefault().postSticky("I'm from MainActivity")
            SecondActivity.start(this)
        })
    }

    private fun testData() {
        getHomeData(netCallBack = object : NetCallBack<HomeData> {
            override fun onSuccess(t: HomeData) {
                homeData = t
                initRcy()
            }

            override fun onError(errorMsg: String) {
            }
        })
    }

    private fun initRcy() {
        var layoutManager = VirtualLayoutManager(this)
        val delegateAdapter = DelegateAdapter(layoutManager, true)
        rcy.layoutManager = layoutManager
        rcy.adapter = delegateAdapter


        delegateAdapter.setAdapters(getAdapters())
        rcy.recycledViewPool = viewPool

    }


    private fun getAdapters(): List<DelegateAdapter.Adapter<*>> {
        val adapters = LinkedList<DelegateAdapter.Adapter<*>>()
        adapters.add(initBanner())
        adapters.add(initNavigation())
        adapters.add(initOnePlus())
        adapters.add(initTitle("推荐套餐样板间"))
        return adapters
    }

    private fun initBanner(): BaseDelegateAdapter {
        return object : BaseDelegateAdapter(this@MainActivity, LinearLayoutHelper(), R.layout.item_home_banner, 1, 1) {
            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                var banner = holder.getView<ImageView>(R.id.img_item_home)
                banner?.loadImage(homeData?.banner?.get(position)?.extendDic?.image!!)
            }
        }
    }

    private fun initNavigation(): BaseDelegateAdapter {
        //menu
        // 在构造函数设置每行的网格个数
        val gridLayoutHelper = GridLayoutHelper(5)
        gridLayoutHelper.setPadding(0, 16, 0, 16)
        gridLayoutHelper.vGap = 16   // 控制子元素之间的垂直间距
        gridLayoutHelper.hGap = 28    // 控制子元素之间的水平间距
        gridLayoutHelper.bgColor = Color.WHITE
        return object : BaseDelegateAdapter(this@MainActivity, gridLayoutHelper, R.layout.item_home_navigation, 5, 2) {
            override fun onBindViewHolder(holder: BaseViewHolder, @SuppressLint("RecyclerView") position: Int) {
                super.onBindViewHolder(holder, position)
                homeData?.navigations?.get(position)?.title?.let { holder.setText(R.id.txt_navigation_desc, it) }
                homeData?.navigations?.get(position)?.extendDic?.image?.let {
                    holder.getView<ImageView>(viewId = R.id.img_navigation_logo)?.loadImage(it)
                }
            }
        }
    }

    private fun initOnePlus(): BaseDelegateAdapter {
        val onePlusNLayoutHelper = OnePlusNLayoutHelper()
        onePlusNLayoutHelper.setMargin(0, 0, 0, 0)
        onePlusNLayoutHelper.setPadding(10, 20, 10, 80)
        return object : BaseDelegateAdapter(this@MainActivity, onePlusNLayoutHelper, R.layout.item_home_oneplus, 4, 3) {
            override fun onBindViewHolder(holder: BaseViewHolder, @SuppressLint("RecyclerView") position: Int) {
                super.onBindViewHolder(holder, position)
                homeData?.recommend?.get(position).apply {
                    holder.setText(R.id.txt_oneplus_title, this!!.title)
                    holder.getView<ImageView>(R.id.img_oneplus_logo)?.loadImage(this!!.extendDic?.image!!)
                }
            }
        }
    }

    private fun initTitle(title: String): BaseDelegateAdapter {
        return object : BaseDelegateAdapter(this@MainActivity, LinearLayoutHelper(), R.layout.item_home_title, 1, 4) {
            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.setText(R.id.txt_title_title, title)
            }
        }
    }
}
