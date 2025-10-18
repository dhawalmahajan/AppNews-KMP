package com.example.appnews.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appnews.navigation.NavigationSideBar
import com.example.appnews.navigation.NewsBottomNavigationBar
import com.example.appnews.navigation.graphs.RootNavGraph
import com.example.appnews.ui.settings.SettingsViewModel
import com.example.appnews.utils.bottomNavigationItemList
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun MainScreen(
    settingsViewModel: SettingsViewModel
) {
    val windowSizeClass = calculateWindowSizeClass()
    val isMediumExpandedWindowSize by remember(windowSizeClass) {
        derivedStateOf {
            windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
        }
    }
    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf { navBackStackEntry?.destination?.route }
    }
    val navigationItem by remember {
        derivedStateOf {
            bottomNavigationItemList.find { it.route == currentRoute }
        }
    }
    val isMainScreenVisible by remember(isMediumExpandedWindowSize) {
        derivedStateOf {
            navigationItem != null
        }
    }
    val isBottomBarVisible by remember {
        derivedStateOf {
            if (!isMediumExpandedWindowSize) {
                navigationItem != null
            } else {
                false
            }
        }
    }
    Row {
        AnimatedVisibility(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            visible = isMediumExpandedWindowSize && isMainScreenVisible,
            enter = slideInHorizontally(
                initialOffsetX = { fullHeight -> -fullHeight }
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { fullHeight ->
                    -fullHeight
                }
            )
        ) {
            NavigationSideBar(
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
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = isBottomBarVisible,
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


}