package com.example.appnews.ui.settings.components

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.apply
import appnews.composeapp.generated.resources.cancel
import appnews.composeapp.generated.resources.choose_a_theme
import appnews.composeapp.generated.resources.delete
import appnews.composeapp.generated.resources.delete_bookmark
import appnews.composeapp.generated.resources.delete_bookmark_description
import com.example.appnews.theme.mediumPadding
import com.example.appnews.theme.xLargePadding
import com.example.appnews.theme.xSmallPadding
import com.example.appnews.utils.Theme
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBookmarkDialog(
    onDeleteBookmark: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(stringResource(Res.string.delete_bookmark))
        },
        text = {

            Text(stringResource(Res.string.delete_bookmark_description))
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(Res.string.delete_bookmark),
                tint = MaterialTheme.colorScheme.onSurface
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onDeleteBookmark()
            }) {
                Text(stringResource(Res.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissRequest()
            }) {
                Text(stringResource(Res.string.cancel))
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDialog(
    currentTheme: String,
    onThemeChange: (Theme) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var currentSelectedTheme by remember {
        mutableStateOf(Theme.valueOf(currentTheme))
    }
    BasicAlertDialog(onDismissRequest = onDismissRequest, content = {
        Surface(
            modifier = Modifier.wrapContentSize(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(mediumPadding)) {
                Text(
                    text = stringResource(Res.string.choose_a_theme),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(xSmallPadding)
                )
                Theme.entries.forEach { theme ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                currentSelectedTheme = theme
                            }, horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = currentSelectedTheme == theme, onClick = {
                                currentSelectedTheme = theme
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(text = stringResource(theme.title))
                    }
                }
                Spacer(modifier = Modifier.padding(xLargePadding))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismissRequest) {
                        Text(stringResource(Res.string.cancel))
                    }
                    Spacer(modifier = Modifier.width(mediumPadding))
                    TextButton(onClick = {
                        onThemeChange(currentSelectedTheme)
                    }) {
                        Text(stringResource(Res.string.apply))
                    }
                }
            }
        }
    })
}