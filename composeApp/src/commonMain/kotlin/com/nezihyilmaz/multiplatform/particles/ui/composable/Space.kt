package com.nezihyilmaz.multiplatform.particles.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure

import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.layout.onSizeChanged
import com.nezihyilmaz.multiplatform.particles.MainRes
import com.nezihyilmaz.multiplatform.particles.controller.Engine
import com.nezihyilmaz.multiplatform.particles.images.MainResImages
import com.nezihyilmaz.multiplatform.particles.ui.modifier.handleGestures

@Composable
fun Space(engine: Engine) {
    val time by Time()
    Box(
        modifier = Modifier.fillMaxSize().onSizeChanged { size ->
            engine.create(
                size.width.toFloat(),
                size.height.toFloat()
            )
        }.handleGestures(engine)
    )
    {
        Fabric(Modifier.fillMaxSize())
        Canvas(modifier = Modifier.fillMaxSize()) {
            engine.update(time)
            renderParticles(engine.particles)




        }


    }



    //Bitmap.makeFromImage(org.jetbrains.skia.Image.).
    //MainResImages.android_logo_transparent.painterResource()

}