package com.example.appnews.ui.common

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import com.example.appnews.theme.imageSize
import com.example.appnews.theme.mediumPadding
import com.example.appnews.theme.shimmer
import com.example.appnews.theme.xLargePadding
import com.example.appnews.theme.xxLargePadding
import com.example.appnews.theme.xxSmallPadding
import com.example.appnews.theme.xxxLargePadding
import com.example.appnews.utils.Type
import com.example.appnews.utils.getType


@Composable
fun ShimmerEffect() {
    val isDesktop = remember {
        getType() == Type.Desktop
    }
    LazyVerticalGrid(
        GridCells.Fixed(if (isDesktop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding),
        userScrollEnabled = false
    ) {
        repeat(12) {
            item {
                ArticleCardShimmerEffect()
            }
        }

    }
}

@Composable
fun ArticleCardShimmerEffect() {
    Row(horizontalArrangement = Arrangement.spacedBy(mediumPadding)) {
        Box(
            modifier = Modifier
                .size(imageSize)
                .shimmerEffect()
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(xxSmallPadding)
        ) {
            Box(modifier = Modifier.fillMaxSize().height(xxxLargePadding).shimmerEffect())

            Box(modifier = Modifier.fillMaxSize().height(xxLargePadding).shimmerEffect())
            Box(modifier = Modifier.fillMaxSize().height(mediumPadding).shimmerEffect())
        }
    }
}

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(
                1500, easing = LinearOutSlowInEasing
            ), repeatMode = RepeatMode.Reverse
        )
    )

    val shimmerColors = listOf<Color>(
        shimmer.copy(0.3f),
        shimmer.copy(0.5f),
        shimmer.copy(1.0f),
        shimmer.copy(0.5f),
        shimmer.copy(0.3f),
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation, translateAnimation),
        end = Offset(translateAnimation + 100f, translateAnimation + 100f),
        tileMode = TileMode.Mirror
    )
    background(brush)
}