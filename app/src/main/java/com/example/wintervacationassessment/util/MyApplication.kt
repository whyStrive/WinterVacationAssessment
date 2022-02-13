package com.example.wintervacationassessment.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Author by why
 * Date on 2022/2/11
 * Time on 18:31
 * Email: why_wanghy@qq.com
 */

//创建MyApplication类，继承Application，实现全局获取context
class MyApplication :Application(){

    companion object{
        //context设置为静态变量很容易内存泄漏，加上注解忽略警告
        //全局只有一份实例，不存在内存泄漏风险
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        //创建时获取context
        context=applicationContext
    }
}