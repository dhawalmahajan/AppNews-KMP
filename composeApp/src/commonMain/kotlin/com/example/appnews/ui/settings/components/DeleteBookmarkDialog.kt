package com.example.appnews.ui.settings.components

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.cancel
import appnews.composeapp.generated.resources.delete
import appnews.composeapp.generated.resources.delete_bookmark
import appnews.composeapp.generated.resources.delete_bookmark_description
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
                contentDescription = stringResource(Res.string.delete_bookmark)
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