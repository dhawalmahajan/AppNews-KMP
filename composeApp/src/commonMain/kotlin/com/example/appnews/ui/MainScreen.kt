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
import com.example.appnews.navigation.NewsBottomNavigationBar
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
    var currentRoute by rememberSaveable(navBackStackEntry) {
        mutableStateOf(navBackStackEntry?.destination?.route)
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
                    onClick = { /* TODO */ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings, contentDescription = "Settings"
                    )

                }

            }
        )
    }, bottomBar = {
        NewsBottomNavigationBar(
            bottomNavigationItemList = bottomNavigationItemList,
            currentRoute = currentRoute,
            onItemCLicked = { currentBottomNavigationItem ->
                currentRoute = currentBottomNavigationItem.route
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
            paddingValues = it
        )
    }

}