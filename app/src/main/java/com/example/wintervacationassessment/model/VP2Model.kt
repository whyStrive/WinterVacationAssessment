package com.example.wintervacationassessment.model

import com.google.gson.annotations.SerializedName

/**
 * Author by why
 * Date on 2022/2/13
 * Time on 15:11
 * Email: why_wanghy@qq.com
 */
data class BannerBean(
    //反射获取信息
    @SerializedName("banners")
    val banner:List<BannerData>?=null
)

data class BannerData(
    @SerializedName("pic")
    val pic:String?=null
)
