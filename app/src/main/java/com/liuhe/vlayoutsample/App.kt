package com.liuhe.vlayoutsample

import android.app.Application

/**
 * @author liuhe
 * @date 2018/3/6
 */
class App : Application() {

    companion object {
        lateinit var sApp: App
    }

    override fun onCreate() {
        super.onCreate()
        sApp = this
    }
}