package com.lvkang.ppjoke

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.lvkang.ppjoke.ui.view.AppBottomBar
import com.lvkang.ppjoke.utils.NavGraphBuilder

class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomView: AppBottomBar = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        NavigationUI.setupWithNavController(bottomView, navController)
        NavGraphBuilder.build(navController)

        //AppBottomBar的点击事件 和 navController 关联起来
        bottomView.setOnNavigationItemSelectedListener(this::onNavItemSelected)
    }

    private fun onNavItemSelected(menuItem: MenuItem): Boolean {
        navController?.navigate(menuItem.itemId)
        //返回 false 代表按钮没有被选中，也不会着色。如果为 true，就会着色，有一个上下浮动的效果
        return !TextUtils.isEmpty(menuItem.title)
    }
}
