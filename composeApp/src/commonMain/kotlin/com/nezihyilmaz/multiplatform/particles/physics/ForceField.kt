package com.nezihyilmaz.multiplatform.particles.physics

/**
 * A [ForceField] is a region of space that exerts a force on any particle that is inside it.
 */
class ForceField(
    val center: Vector2,
    val radius: Float,
    val forceGenerator: (directionUnitVector: Vector2, distanceFraction: Float, distance: Float) -> Vector2,
) {

    fun interact(particle: Particle) : InteractionResult{
        //get the distance vector between the particle and the center of the force field
        val distanceVector = particle.position.distanceVector(center)
        val distance = distanceVector.magnitude()
        if (distance > radius) return InteractionResult.invalidInteraction
        //get the fraction of the distance between the particle and the center of the force field relative to the radius
        val distanceFraction = 1 - (distance / radius)
        //generate the force vector and apply it to the particle
        val force = forceGenerator(distanceVector.normalized(), distanceFraction, distance)
        particle.applyForce(force)
        return InteractionResult(true, force)
    }

}

data class InteractionResult(val valid: Boolean, val force: Vector2?){
    companion object{
        val invalidInteraction = InteractionResult(false, null)
    }
}