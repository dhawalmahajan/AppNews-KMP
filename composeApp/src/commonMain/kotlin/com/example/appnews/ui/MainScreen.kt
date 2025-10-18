package com.example.appnews.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appnews.navigation.NewsBottomNavigationBar
import com.example.appnews.navigation.graphs.RootNavGraph
import com.example.appnews.ui.settings.SettingsViewModel
import com.example.appnews.utils.bottomNavigationItemList
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen(
    settingsViewModel: SettingsViewModel
) {
    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf { navBackStackEntry?.destination?.route }
    }
    val bottomNavRoute by remember {
        derivedStateOf {
            bottomNavigationItemList.find { it.route == currentRoute }
        }
    }
    val bottomBarVisibility by remember {
        derivedStateOf {
            bottomNavRoute != null
        }
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisibility,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight ->
                        fullHeight
                    }
                )
            ) {
                NewsBottomNavigationBar(
                    bottomNavigationItemList = bottomNavigationItemList,
                    currentRoute = currentRoute,
                    onItemCLicked = { currentBottomNavigationItem ->
                        rootNavController.navigate(currentBottomNavigationItem.route) {
                            rootNavController.graph.startDestinationRoute?.let {
                                popUpTo(it) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

        }) { paddingValues ->
        RootNavGraph(rootNavController, paddingValues, settingsViewModel)

    }

}