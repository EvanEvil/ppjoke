package com.lvkang.ppjoke.utils

import android.content.ComponentName
import android.util.Log
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphNavigator
import androidx.navigation.fragment.FragmentNavigator

/**
 * @name ppjoke
 * @class name：com.lvkang.ppjoke.utils
 * @author 345 QQ:1831712732
 * @time 2020/3/9 22:30
 * @description
 */
class NavGraphBuilder {
    companion object {
        fun build(navController: NavController) {

            val provider = navController.navigatorProvider

            //节点集合
            val navGraph = NavGraph(NavGraphNavigator(provider))

            //获取已经注册的导航
            val fragmentNavigator = provider.getNavigator(FragmentNavigator::class.java)
            val activityNavigator = provider.getNavigator(ActivityNavigator::class.java)

            val destConfig = AppConfig.getDestConfig()
            destConfig.forEach {
                if (it.value.isFragment) {
                    val destination = fragmentNavigator.createDestination()
                    destination.className = it.value.className
                    destination.id = it.value.id
                    destination.addDeepLink(it.value.pageUrl)
                    //添加节点
                    navGraph.addDestination(destination)
                } else {
                    val destination = activityNavigator.createDestination()
                    destination.id = it.value.id
                    destination.addDeepLink(it.value.pageUrl)
                    destination.setComponentName(
                        ComponentName(
                            AppGlobals.getApplication().packageName,
                            it.value.className
                        )
                    )
                    navGraph.addDestination(destination)
                }

                if (it.value.asStarter) {
                    navGraph.startDestination = it.value.id
                }
            }
            //设置新的节点集合
            navController.graph = navGraph
        }

    }
}