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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import com.example.appnews.theme.cardMinSize
import com.example.appnews.theme.imageSize
import com.example.appnews.theme.mediumPadding
import com.example.appnews.theme.shimmerColors
import com.example.appnews.theme.xSmallPadding
import com.example.appnews.theme.xxSmallPadding
import com.example.appnews.theme.xxxLargePadding


@Composable
fun ShimmerEffect() {
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


    val brush by remember {
        derivedStateOf {
            Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(translateAnimation, translateAnimation),
                end = Offset(translateAnimation + 100f, translateAnimation + 100f),
                tileMode = TileMode.Mirror
            )
        }
    }


    LazyVerticalStaggeredGrid(
        StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
        userScrollEnabled = false
    ) {
        repeat(30) {
            item {
                ArticleCardShimmerEffect(brush)
            }
        }

    }
}

@Composable
fun ArticleCardShimmerEffect(brush: Brush) {
    Row(horizontalArrangement = Arrangement.spacedBy(xSmallPadding)) {
        Box(
            modifier = Modifier
                .size(imageSize)
                .background(brush, shape = RoundedCornerShape(10))
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(xxSmallPadding)
        ) {
            Box(
                modifier = Modifier.fillMaxSize().height(xxxLargePadding)
                    .background(brush, shape = RoundedCornerShape(10))
            )

            Box(
                modifier = Modifier.fillMaxSize().height(xxxLargePadding)
                    .background(brush, shape = RoundedCornerShape(10))
            )

            Box(
                modifier = Modifier.fillMaxSize().height(xxxLargePadding)
                    .background(brush, shape = RoundedCornerShape(10))
            )
        }
    }
}

