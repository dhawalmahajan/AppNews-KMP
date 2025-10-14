package com.example.appnews.ui.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.delete_bookmark
import appnews.composeapp.generated.resources.ic_delete
import appnews.composeapp.generated.resources.ic_light_mode
import appnews.composeapp.generated.resources.setting
import appnews.composeapp.generated.resources.theme
import com.example.appnews.ui.settings.components.DeleteBookmarkDialog
import com.example.appnews.ui.settings.components.SettingItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SettingScreen(rootNavController: NavHostController) {
    var showDeleteBookmarkDialog by remember {
        mutableStateOf(false)
    }
    var showThemeSelectionDialog by remember {
        mutableStateOf(false)
    }
    when {
        showThemeSelectionDialog -> {

        }

        showDeleteBookmarkDialog -> {
            DeleteBookmarkDialog(onDismissRequest = {
                showDeleteBookmarkDialog = false
            }, onDeleteBookmark = {
                showDeleteBookmarkDialog = false
            })
        }
    }

    Scaffold(
        topBar =
            {
                TopAppBar(
                    title = {
                        Text(stringResource(Res.string.setting))
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                rootNavController.navigateUp()
                            }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(Res.string.setting),

                                )
                        }
                    }
                )
            },
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            item {
                SettingItem(
                    onClick = {
                        showThemeSelectionDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_light_mode),
                    itemName = stringResource(Res.string.theme)
                )
            }
            item {
                SettingItem(
                    onClick = {
                        showDeleteBookmarkDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_delete),
                    itemName = stringResource(Res.string.delete_bookmark),
                    itemColor = MaterialTheme.colorScheme.error

                )
            }
        }

    }
}