package com.example.appnews.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsBottomNavigationBar(
    bottomNavigationItemList: List<BottomNavigationItem>,
    currentRoute: String?,
    onItemCLicked: (BottomNavigationItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        bottomNavigationItemList.forEach { bottomNavigationItem ->
            NavigationBarItem(
                selected = currentRoute == bottomNavigationItem.route,
                onClick = {
                    onItemCLicked(bottomNavigationItem)
                },
                icon = {
                    Icon(
                        painter = painterResource(bottomNavigationItem.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(
                            bottomNavigationItem.title
                        ),
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis

                    )

                },
                alwaysShowLabel = true
            )

        }
    }
}