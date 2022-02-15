package com.example.wintervacationassessment.Model

import com.google.gson.annotations.SerializedName

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 12:10
 * Email: why_wanghy@qq.com
 */
data class SongsBean(
    @SerializedName("songs")
    val songs:List<SongsData>?=null
)

data class SongsData(
    @SerializedName("name")
    val name:String?=null,
    @SerializedName("id")
    val id:String?=null
)
