package com.example.wintervacationassessment.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.util.showToast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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