package com.example.wintervacationassessment.util

import android.widget.Toast

/**
 * Author by why
 * Date on 2022/2/11
 * Time on 18:29
 * Email: why_wanghy@qq.com
 */

//使用扩展函数简化Toast的写法
fun String.showToast(length:Int=Toast.LENGTH_SHORT){   //使用参数默认值，再减少代码量（懒人泪目）
    //使用MyApplication获取的context，进一步简化Toast写法（懒人必备）
    Toast.makeText(MyApplication.context,this,length).show()
}