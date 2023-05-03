package by.vfedorenko.mvistarter.ui.compose.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize

@Composable
fun ShimmeryContent(
    modifier: Modifier = Modifier,
    shimmerVisible: Boolean,
    content: @Composable () -> Unit
) {
    var boxSize by remember { mutableStateOf(IntSize.Zero) }

    Box(modifier = modifier.onGloballyPositioned { boxSize = it.size }) {
        content()

        AnimatedVisibility(
            visible = shimmerVisible,
            exit = fadeOut(),
            enter = EnterTransition.None
        ) {
            ShimmerContent(
                widthPx = boxSize.width,
                heightPx = boxSize.height
            )
        }
    }
}

@Composable
private fun ShimmerContent(
    widthPx: Int,
    heightPx: Int,
) {
    val colors = listOf(
        Color.Gray,
        Color.LightGray,
        Color.Gray
    )

    val gradientWidth: Float = (0.2f * heightPx)

    val infiniteTransition = rememberInfiniteTransition()

    val xShimmer by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (widthPx + gradientWidth),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1300,
                easing = LinearEasing,
                delayMillis = 300
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val yShimmer by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (heightPx + gradientWidth),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1300,
                easing = LinearEasing,
                delayMillis = 300
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )

    val width = LocalDensity.current.run { widthPx.toDp() }
    val height = LocalDensity.current.run { heightPx.toDp() }

    Spacer(
        modifier = Modifier
            .size(width, height)
            .background(brush = brush)
    )
}
