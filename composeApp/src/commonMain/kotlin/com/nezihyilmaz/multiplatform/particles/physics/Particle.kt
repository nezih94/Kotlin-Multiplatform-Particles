package com.nezihyilmaz.multiplatform.particles.physics

import androidx.compose.ui.graphics.Color

data class Particle(
    val position: Vector2,
    val velocity: Vector2 = Vector2(0f, 0f),
    val acceleration: Vector2 = Vector2(0f, 0f),
    val instinctForce: ForceField,
    val particleProperties: ParticleProperties
) {

    fun applyForce(force: Vector2) {
        acceleration += force
    }

    fun update(limit: Float? = null) {
        velocity += acceleration
        limit?.let { velocity.limit(it) }
        position += velocity
        acceleration *= 0f
    }
}

data class ParticleProperties(
    val color: Color,
    val radius: Float
)
