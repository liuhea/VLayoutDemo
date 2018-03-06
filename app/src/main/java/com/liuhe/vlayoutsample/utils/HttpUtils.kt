package com.liuhe.vlayoutsample.utils

import com.liuhe.vlayoutsample.HomeData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * @author liuhe
 * @date 2018/3/6
 */
var retrofit = Retrofit.Builder()
        //设置OKHttpClient,如果不设置会提供一个默认的
        .client(OkHttpClient())
        .baseUrl("http://www.wanandroid.com/tools/mockapi/2636/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()!!

val service = retrofit.create(HomeService::class.java)!!

interface NetCallBack<T> {
    fun onSuccess(t: T)
    fun onError(errorMsg: String)
}


interface HomeService {
    @GET("shejjiahome")
    fun getHomeData(): Call<HomeData>
}


fun getHomeData(netCallBack: NetCallBack<HomeData>) {
    val homeData = service.getHomeData()
    homeData.enqueue(object : retrofit2.Callback<HomeData> {
        override fun onFailure(call: Call<HomeData>?, t: Throwable?) {
            netCallBack.onError(t.toString())
        }

        override fun onResponse(call: Call<HomeData>?, response: Response<HomeData>?) {
            response?.body()?.let { netCallBack.onSuccess(it) }
        }
    })
}

