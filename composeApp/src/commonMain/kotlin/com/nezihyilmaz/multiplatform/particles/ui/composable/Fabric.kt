package com.nezihyilmaz.multiplatform.particles.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


/**
 * A [Fabric] is a grid of lines that is used to visualize the space.
 */
@Composable
fun Fabric(modifier: Modifier) {

    Canvas(modifier = modifier) {
        drawRect(color = Color.LightGray)
        val canvasWidth = size.width
        val canvasHeight = size.height
        val lineGap = 150f
        val strokeWidth = 4f

        val verticalGradient = Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Gray, Color.Transparent)
        )

        val horizontalGradient = Brush.horizontalGradient(
            colors = listOf(Color.Transparent, Color.Gray, Color.Transparent)
        )

        // draw vertical lines
        var x = 0f
        while (x < canvasWidth) {
            drawLine(
                brush = verticalGradient,
                start = Offset(x, 0f),
                end = Offset(x, canvasHeight),
                strokeWidth = strokeWidth
            )
            x += lineGap
        }

        // draw horizontal lines
        var y = 0f
        while (y < canvasHeight) {
            drawLine(
                brush = horizontalGradient,
                start = Offset(0f, y),
                end = Offset(canvasWidth, y),
                strokeWidth = strokeWidth
            )
            y += lineGap
        }
    }
}