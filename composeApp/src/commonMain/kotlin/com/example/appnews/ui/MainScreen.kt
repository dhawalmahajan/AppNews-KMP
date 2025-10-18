package com.example.appnews.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.setting
import com.example.appnews.data.database.NewsDao
import com.example.appnews.navigation.NewsBottomNavigationBar
import com.example.appnews.navigation.SettingRouteScreen
import com.example.appnews.navigation.graphs.MainNavGraph
import com.example.appnews.utils.bottomNavigationItemList
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen(rootNavController: NavHostController) {
    val homeNavController = rememberNavController()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    var previousRoute by rememberSaveable {
        mutableStateOf(navBackStackEntry?.destination?.route)
    }
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf { navBackStackEntry?.destination?.route }
    }
    val topBarTitle by remember(currentRoute) {
        derivedStateOf {
            if (currentRoute != null) {
                bottomNavigationItemList[bottomNavigationItemList.indexOfFirst { it.route == currentRoute }].title
            } else {
                bottomNavigationItemList[0].title
            }

        }
    }
    DisposableEffect(Unit) {

        println("previous route = $previousRoute")
        onDispose {
            previousRoute = currentRoute
        }
    }
    LaunchedEffect(Unit) {
        if (previousRoute != null) {
            homeNavController.navigate(previousRoute!!) {
                homeNavController.graph.startDestinationRoute?.let {
                    popUpTo(it) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    stringResource(topBarTitle),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }, actions = {
                IconButton(
                    onClick = {
                        rootNavController.navigate(SettingRouteScreen.Setting.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings, contentDescription = stringResource(
                            Res.string.setting
                        )
                    )

                }

            }
        )
    }, bottomBar = {
        NewsBottomNavigationBar(
            bottomNavigationItemList = bottomNavigationItemList,
            currentRoute = currentRoute,
            onItemCLicked = { currentBottomNavigationItem ->
                homeNavController.navigate(currentBottomNavigationItem.route) {
                    homeNavController.graph.startDestinationRoute?.let {
                        popUpTo(it) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }) {
        MainNavGraph(
            rootNavController = rootNavController,
            homeNavController = homeNavController,
            paddingValues = it,
        )
    }

}