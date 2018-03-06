package com.liuhe.vlayoutsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRcy()
    }

    private fun initRcy() {
        var layoutManager = VirtualLayoutManager(this)
        rcy.layoutManager = layoutManager
        val delegateAdapter = DelegateAdapter(layoutManager, true)
        rcy.adapter = delegateAdapter
        delegateAdapter.setAdapters(getAdapters())
    }

    private fun getAdapters(): List<DelegateAdapter.Adapter<*>> {
        val adapters = LinkedList<DelegateAdapter.Adapter<*>>()
        val layoutHelper = GridLayoutHelper(4)
        layoutHelper.setMargin(0, 10, 0, 10)
        layoutHelper.hGap = 3
        layoutHelper.aspectRatio = 4f
        adapters.add(SubAdapter(this, layoutHelper, 8))
        return adapters
    }
}
