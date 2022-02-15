package com.example.wintervacationassessment.model

import com.google.gson.annotations.SerializedName

/**
 * Author by why
 * Date on 2022/2/14
 * Time on 15:39
 * Email: why_wanghy@qq.com
 */
data class RCSongListBean(
    @SerializedName("result")
    val rcSongList:List<RcSongListData>?=null
)

data class RcSongListData(
    @SerializedName("picUrl")
    val picUrl:String?=null,
    @SerializedName("name")
    val name:String?=null
)