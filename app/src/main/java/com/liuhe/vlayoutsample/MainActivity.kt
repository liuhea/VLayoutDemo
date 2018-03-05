package com.liuhe.vlayoutsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.vlayout.VirtualLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutAdapter


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

//        delegateAdapter.addAdapter()

    }
}