package com.example.appnews.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.appnews.data.model.Article
import com.example.appnews.theme.imageSize
import com.example.appnews.theme.mediumPadding


@Composable
fun ArticleItem(
    article: Article,
    onItemClick: () -> Unit
) {
    Row(modifier = Modifier.clickable {
        onItemClick()
    }, horizontalArrangement = Arrangement.spacedBy(mediumPadding)) {
        Box(
            modifier = Modifier
                .size(imageSize)
                .clip(MaterialTheme.shapes.large)
                .background(Color.Gray)
        )
        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(
                mediumPadding
            )
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontWeight = FontWeight.Bold
            )
            article.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
            Text(
                text = article.source.name,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}