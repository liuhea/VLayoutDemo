package com.liuhe.vlayoutsample.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.liuhe.vlayoutsample.R
import kotlinx.android.synthetic.main.activity_second.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by liuhe on 14/03/2018.
 */
class SecondActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            var intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        EventBus.getDefault().register(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun receiveMsg(msg: String) {
        txt_second_received.text = msg
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}