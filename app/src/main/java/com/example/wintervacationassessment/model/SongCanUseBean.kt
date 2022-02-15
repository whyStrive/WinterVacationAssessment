package com.example.wintervacationassessment.model

import com.google.gson.annotations.SerializedName

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 12:55
 * Email: why_wanghy@qq.com
 */


data class SongCanUseData(
    @SerializedName("success")
    val result:Boolean=false,
    @SerializedName("message")
    val message:String="亲爱的，暂无版权"
)