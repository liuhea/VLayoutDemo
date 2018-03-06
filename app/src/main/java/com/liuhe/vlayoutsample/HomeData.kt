package com.liuhe.vlayoutsample

import com.google.gson.annotations.SerializedName

/**
 * @author liuhe
 * @date 2018/3/6
 */

class HomeData(val designers: List<Designer>, val cases: List<Case>,
               val navigations: List<Navigation>, val banner: List<Banner>,
               val recommend: List<Recommend>)

/**
 * 设计师信息
 */
class Designer(@SerializedName("designer_name") val designerName: String, @SerializedName("designer_avatar") val designerAvatar: String)

/**
 * 案例信息
 */
class Case(val style: String, val cover: String)

/**
 * 导航信息
 */
class Navigation(val title: String, @SerializedName("extend_dic") val extendDic: ExtendDic)

/**
 * 轮播图
 */
class Banner(@JvmField @SerializedName("extend_dic") val extendDic: ExtendDic)

/**
 * @param image 图片地址
 * @param url h5 url
 */
class ExtendDic(val image: String, val url: String)

/**
 * 推荐信息 1+N
 */
class Recommend(val title: String, val subTitle: String, @SerializedName("extend_dic") val extendDic: ExtendDic)
