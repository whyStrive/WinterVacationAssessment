package com.example.wintervacationassessment.model

import com.google.gson.annotations.SerializedName

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 19:59
 * Email: why_wanghy@qq.com
 */

data class MusicUrlBean(
    @SerializedName("data")
    val musics:List<MusicData>?=null
)

data class MusicData(
    @SerializedName("url")
    val url:String?=null
)