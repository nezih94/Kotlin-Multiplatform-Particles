package com.nezihyilmaz.multiplatform.particles.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import com.nezihyilmaz.multiplatform.particles.controller.Engine
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
}