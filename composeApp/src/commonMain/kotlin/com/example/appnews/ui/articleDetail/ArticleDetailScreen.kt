package com.example.appnews.ui.articleDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import appnews.composeapp.generated.resources.Res
import appnews.composeapp.generated.resources.ic_bookmark_outlined
import appnews.composeapp.generated.resources.ic_browse
import appnews.composeapp.generated.resources.logo
import appnews.composeapp.generated.resources.news_detail
import appnews.composeapp.generated.resources.setting
import coil3.compose.AsyncImage
import com.example.appnews.data.model.Article
import com.example.appnews.theme.detailImageSize
import com.example.appnews.theme.xLargePadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(navController: NavController, article: Article) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.news_detail),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.setting),
                            tint = MaterialTheme.colorScheme.onSurface


                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null,

                            )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_browse),
                            contentDescription = null,

                            )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_bookmark_outlined),
                            contentDescription = null,

                            )
                    }
                }
            )
        },

        ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding), contentPadding = PaddingValues(
                xLargePadding
            ),
            verticalArrangement = Arrangement.spacedBy(xLargePadding)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .height(detailImageSize)
                        .clip(MaterialTheme.shapes.large)
                        .background(Color.Gray),
                    model = article.urlToImage,
                    error = painterResource(Res.drawable.logo),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            item {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            article.description?.let {
                item {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            article.publishedAt.let {
                item {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

    }
}