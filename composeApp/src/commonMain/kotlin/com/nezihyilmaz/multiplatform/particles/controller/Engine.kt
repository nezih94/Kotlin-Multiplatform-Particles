package com.nezihyilmaz.multiplatform.particles.controller

import androidx.compose.ui.graphics.Color
import com.nezihyilmaz.multiplatform.particles.getUIData
import com.nezihyilmaz.multiplatform.particles.physics.ForceField
import com.nezihyilmaz.multiplatform.particles.physics.Particle
import com.nezihyilmaz.multiplatform.particles.physics.ParticleProperties
import com.nezihyilmaz.multiplatform.particles.physics.Vector2
import kotlin.math.PI
import kotlin.math.pow

class Engine {

    val pointerForceRadius = 300f
    val pointerForceFieldBaseStrength = 30f
    val pointerForceFieldStrengthCurveSwapDistanceFractionThreshold = 0.8f
    val pointerForceFieldStrengthWaveScaling = 4f

    val instinctForceRadius = 10000f
    val instinctForceBaseStrength = 1.5f

    val maxLimit : Float? = 12f

    var particles: MutableList<Particle> = mutableListOf()
    var pointerForceField: ForceField? = null

    fun create(width: Float, height: Float) {

        val uiData = getUIData(width, height)
        val particleProperties = uiData.particleProperties ?: ParticleProperties(Color.Red, 6f)
        val particleList = uiData.coordinates.map {
            val position = Vector2(it.first, it.second)
            Particle(position = position,
                instinctForce = ForceField(center = position.copy(),
                    radius = instinctForceRadius,
                    forceGenerator = { directionUnitVector, distanceFraction, distance ->
                        val gravity = directionUnitVector.rotate(PI.toFloat()) * (distanceFraction) * instinctForceBaseStrength
                        gravity
                    }), particleProperties = particleProperties)
        }
        particles = particleList.toMutableList()
    }

    fun update(time: Float) {

        fun getIdleLimit(distance: Float) : Float? {
            if (maxLimit == null) return null
            val limit = (distance / pointerForceRadius) * maxLimit
            return if (distance > pointerForceRadius) maxLimit else limit
        }

        particles.forEach {
            val instinctInteraction = it.instinctForce.interact(it)
            val fingerInteraction = pointerForceField?.interact(it)
            val distanceToCenter = it.position.distanceTo(it.instinctForce.center)
            var limit : Float? = if (fingerInteraction?.valid == true) maxLimit else getIdleLimit(distanceToCenter)
            it.update(limit)
        }


    }

    fun createForceField(x: Float, y: Float) {
        pointerForceField = ForceField(Vector2(x, y),
            radius = pointerForceRadius,
            forceGenerator = { directionUnitVector, distanceFraction, distance ->
                val strength = pointerForceFieldBaseStrength
                val swapThreshold = pointerForceFieldStrengthCurveSwapDistanceFractionThreshold
                val unitVector = directionUnitVector.copy()
                val multiplier = if (distanceFraction <= swapThreshold) {
                    (pointerForceFieldStrengthWaveScaling * distanceFraction).pow(strength) / pointerForceFieldStrengthWaveScaling
                } else {
                    ((pointerForceFieldStrengthWaveScaling - pointerForceFieldStrengthWaveScaling * distanceFraction).pow(strength)) / pointerForceFieldStrengthWaveScaling + (1 - swapThreshold)
                }
                unitVector * (multiplier) * strength
            })
    }

    fun moveForceFieldBy(x: Float, y: Float) {
        if (pointerForceField == null) return
        pointerForceField!!.center += Vector2(x, y)
    }

    fun removeForceField() {
        pointerForceField = null
    }



}