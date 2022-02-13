package com.example.wintervacationassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.wintervacationassessment.ViewModel.FindFragViewModel
import com.example.wintervacationassessment.util.showToast


class MainActivity : AppCompatActivity() {

    private lateinit var vm:FindFragViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this).get(FindFragViewModel::class.java)

        //将toolbar的HOME按钮设置为滑动菜单的导航按钮
        val toolbar:androidx.appcompat.widget.Toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        val navView:com.google.android.material.navigation.NavigationView=findViewById(R.id.navView)
        navView.setCheckedItem(R.id.myMessage)
        val drawerLayout:DrawerLayout=findViewById(R.id.drawerLayout)
        navView.setNavigationItemSelectedListener {
            "菜单没时间做了呜呜呜（菜鸡泪奔）".showToast()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //按下HOME，打开左侧的滑动菜单
        val drawerLayout:DrawerLayout=findViewById(R.id.drawerLayout)
        when(item.itemId){
            android.R.id.home->drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}
























//（写在这里反正你们也看不到）
// 佛祖保佑 from  郭神
//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖保佑             永无BUG
//             佛曰:
//                    写字楼里写字间，写字间里程序员；
//                    程序人员写程序，又拿程序换酒钱。
//                    酒醒只在网上坐，酒醉还来网下眠；
//                    酒醉酒醒日复日，网上网下年复年。
//                    但愿老死电脑间，不愿鞠躬老板前；
//                    奔驰宝马贵者趣，公交自行程序员。
//                    别人笑我忒疯癫，我笑自己命太贱；
//                    不见满街漂亮妹，哪个归得程序员？