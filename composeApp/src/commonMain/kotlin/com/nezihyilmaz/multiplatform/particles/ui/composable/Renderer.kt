package com.nezihyilmaz.multiplatform.particles.ui.composable

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.nezihyilmaz.multiplatform.particles.physics.Particle

fun DrawScope.renderParticles(particles: List<Particle>) {
    particles.forEach {
        drawCircle(
            color = it.particleProperties.color,
            radius = it.particleProperties.radius,
            center = Offset(it.position.x, it.position.y)
        )
    }
}